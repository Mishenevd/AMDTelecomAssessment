package task4.iocframework.di;

/**
 * DI framework (IoC implementation).
 *
 * @author Dmitrii_Mishenev
 */
public class DIFramework {

    private static ApplicationContext CONTEXT;

    /**
     * Start application
     *
     * @param mainClass main class
     */
    public static Cache startApplication(Class<?> mainClass) {
        try {
            synchronized (ApplicationContext.class) {
                if (CONTEXT == null) {
                    CONTEXT = new ApplicationContext();
                    CONTEXT.initializeContext(mainClass);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CONTEXT.getCache();
    }
}