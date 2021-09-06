package task4.resource;

import task4.iocframework.di.annotations.Component;

/**
 * Резервный клиент для получения погоды.
 *
 * @author Dmitrii_Mishenev
 */
@Component(name = "backupTemperatureResource")
public class BackupTemperatureResourceImpl implements TemperatureResource {

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