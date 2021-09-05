package task4.resource;

/**
 * Резервный клиент для получения погоды.
 *
 * @author Dmitrii_Mishenev
 */
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