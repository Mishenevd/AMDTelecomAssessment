package task4.iocframework.di;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import task4.iocframework.di.annotations.Autowired;
/**
 * InjectionUtil.
 *
 * @author Dmitrii_Mishenev
 */
public class InjectionUtil {
    private InjectionUtil() {
        super();
    }
    /**
     * Perform injection recursively, for each service inside the Client class
     */
    public static void autowire(Cache context, Class<?> classz, Object classInstance)
            throws InstantiationException, IllegalAccessException {
        Set<Field> fields = findFields(classz);
        for (Field field : fields) {
            String qualifier = field.isAnnotationPresent(Autowired.class)
                    ? field.getAnnotation(Autowired.class).name()
                    : null;
            Object fieldInstance = context.getBeanInstance(field.getType(), qualifier);
            field.set(classInstance, fieldInstance);
            autowire(context, fieldInstance.getClass(), fieldInstance);
        }
    }
    /**
     * Get all the fields having CustomAutowired annotation used while declaration
     */
    private static Set<Field> findFields(Class<?> classz) {
        Set<Field> set = new HashSet<>();
        while (classz != null) {
            for (Field field : classz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    set.add(field);
                }
            }
            classz = classz.getSuperclass();
        }
        return set;
    }
}