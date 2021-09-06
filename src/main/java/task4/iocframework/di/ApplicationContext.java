package task4.iocframework.di;

import java.io.IOException;
import java.util.Set;
import lombok.Getter;
import org.reflections.Reflections;
import task4.iocframework.di.annotations.Component;

class ApplicationContext {
    @Getter
    private final Cache cache = Cache.GET_INSTANCE();

    void initializeContext(Class<?> mainClass) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?>[] classes = ClassLoaderUtil.getClasses(mainClass.getPackage().getName());
        Reflections reflections = new Reflections(mainClass.getPackage().getName());
        Set<Class<?>> types = reflections.getTypesAnnotatedWith(Component.class);
        for (Class<?> implementationClass : types) {
            Class<?>[] interfaces = implementationClass.getInterfaces();
            if (interfaces.length == 0) {
                cache.putInDi(implementationClass, implementationClass);
            } else {
                for (Class<?> iface : interfaces) {
                    cache.putInDi(iface, implementationClass);
                }
            }
        }
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(Component.class)) {
                Object classInstance = clazz.newInstance();
                cache.addService(clazz, classInstance);
                InjectionUtil.autowire(cache, clazz, classInstance);
            }
        }
    }
}