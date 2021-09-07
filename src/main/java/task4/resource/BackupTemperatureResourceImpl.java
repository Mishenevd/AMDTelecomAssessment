package task4.resource;

import task4.service.TemperatureSmsNotificationServiceImpl;

/**
 * Резервный клиент для получения погоды.
 *
 * @author Dmitrii_Mishenev
 */
public class BackupTemperatureResourceImpl implements TemperatureResource {
    private static volatile BackupTemperatureResourceImpl SINGLETON;

    public static BackupTemperatureResourceImpl getInstance() {
        if (SINGLETON != null) {
            return SINGLETON;
        }
        synchronized (TemperatureSmsNotificationServiceImpl.class) {
            if (SINGLETON == null) {
                SINGLETON = new BackupTemperatureResourceImpl();
            }
            return SINGLETON;
        }
    }

    private BackupTemperatureResourceImpl() {
    }

    /**
     * Резервный метод для получения погоды.
     * @param cityName неважно какой город.
     * @return всегда вернёт 20.
     */
    @Override
    public double getTemperature(String cityName) {
        return 20;
    }
}