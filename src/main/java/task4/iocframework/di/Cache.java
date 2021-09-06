package task4.iocframework.di;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import task4.iocframework.di.annotations.Component;

/**
 * 
 *
 * @author Dmitrii_Mishenev
 */
public final class Cache {

    private static volatile Cache INSTANCE;

    final Map<Class<?>, Object> beans;
    final Map<Class<?>, Class<?>> diMap;

    private Cache() {
        beans = new ConcurrentHashMap<>();
        diMap = new ConcurrentHashMap<>();
    }

    public static Cache GET_INSTANCE() {
        Cache result = INSTANCE;
        if (result != null) {
            return result;
        } else {
            synchronized (Cache.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Cache();
                }
                return INSTANCE;
            }
        }
    }

    public <T> T getBean(Class<T> clazz) {
        return (T) beans.get(clazz);
    }

    void putInDi(Class<?> interfaceClass, Class<?> implementationClass) {
        diMap.put(implementationClass, interfaceClass);
    }

    void addService(Class<?> classInstance, Object newService) {
        beans.put(classInstance, newService);
    }

    /**
     * Overload getBeanInstance to handle qualifier and autowire by type
     */
    <T> Object getBeanInstance(Class<T> interfaceClass, String qualifier)
            throws InstantiationException, IllegalAccessException {
        Class<?> implementationClass = getImplementationClass(interfaceClass, qualifier);
        if (beans.containsKey(implementationClass)) {
            return beans.get(implementationClass);
        }
        Object service = implementationClass.newInstance();
        beans.put(implementationClass, service);
        return service;
    }

    /**
     * Get the name of the implementation class for input interface service
     */
    private Class<?> getImplementationClass(Class<?> interfaceClass, final String qualifier) {
        Set<Map.Entry<Class<?>, Class<?>>> implementationClasses = diMap.entrySet().stream()
                .filter(entry -> entry.getValue() == interfaceClass).collect(Collectors.toSet());


        Optional<Map.Entry<Class<?>, Class<?>>> optional = implementationClasses.stream()
                .filter(entry -> entry.getKey().getAnnotation(Component.class).name().equalsIgnoreCase(qualifier)).findAny();

        if (optional.isPresent()) {
            return optional.get().getKey();
        }

        throw new RuntimeException("There are " + implementationClasses.size() + " of interface " + interfaceClass.getName()
                + " Expected single implementation or make use of @CustomQualifier to resolve conflict");
    }
}