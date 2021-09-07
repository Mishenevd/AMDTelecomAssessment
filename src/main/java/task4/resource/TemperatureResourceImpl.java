package task4.resource;

import java.net.URI;
import java.net.http.HttpRequest;
import task4.http.DefaultHttpBadResponseHandler;
import task4.http.HttpFacade;
import task4.http.HttpFacadeImpl;
import task4.http.builder.UriBuilder;
import task4.http.serialization.TemperatureResponseParser;

/**
 * Resource for fetching current temperature in required city using OpenWeather service.
 *
 * @author Dmitrii_Mishenev
 */
public class TemperatureResourceImpl implements TemperatureResource {
    private static volatile TemperatureResourceImpl SINGLETON;

    private static final String REQUEST_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String CITY_PARAM_NAME = "q";
    private static final String MEASURE_SYSTEM_PARAM_NAME = "units";
    private static final String APP_ID_PARAM_NAME = "appId";
    private static final String METRIC = "metric";
    private static final String APP_ID = "b385aa7d4e568152288b3c9f5c2458a5";

    private final HttpFacade<Double> httpFacade;

    public static TemperatureResourceImpl getInstance() {
        if (SINGLETON != null) {
            return SINGLETON;
        }
        synchronized (TemperatureResourceImpl.class) {
            if (SINGLETON == null) {
                SINGLETON = new TemperatureResourceImpl();
            }
            return SINGLETON;
        }
    }

    private TemperatureResourceImpl() {
        httpFacade = new HttpFacadeImpl<>(
                new TemperatureResponseParser(),
                new DefaultHttpBadResponseHandler());
    }

    private TemperatureResourceImpl(HttpFacade<Double> httpFacade) {
        this.httpFacade = httpFacade;
    }

    /**
     * Get current temperature.
     * @param city name of required city.
     * @return current city temperature in celsius.
     */
    public double getTemperature(String city) {
        URI uri = new UriBuilder()
                .spec(REQUEST_URL)
                .param(CITY_PARAM_NAME, city)
                .param(MEASURE_SYSTEM_PARAM_NAME, METRIC)
                .param(APP_ID_PARAM_NAME, APP_ID)
                .build();

        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .GET()
                .uri(uri)
                .build();

        return httpFacade.exchange(httpRequest);
    }
}