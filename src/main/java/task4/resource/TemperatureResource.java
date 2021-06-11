package task4.resource;

/**
 * Resource for fetching current temperature in required city using OpenWeather service.
 *
 * @author Dmitrii_Mishenev
 */
public interface TemperatureResource {

    /**
     * Get current temperature.
     * @param cityName name of required city.
     * @return current city temperature in celsius.
     */
    double getTemperature(String cityName);
}