package task4.service;

import task4.dto.SmsRequestDto;
import task4.resource.RouteeSmsResource;
import task4.resource.TemperatureResource;

/**
 * Service for checking actual temperature and sending SMS with temperature.
 *
 * @author Dmitrii_Mishenev
 */
public class TemperatureSmsNotificationServiceImpl implements TemperatureSmsNotificationService {
    private static final String CITY_NAME = "Naberezhnye Chelny";
    private static final String SENDER_NAME = "Dmitriy Mishenyov";
    private static final String LOW_TEMPERATURE_MSG = "Temperature less than 20C. ";
    private static final String HIGH_TEMPERATURE_MSG = "Temperature more than 20C. ";
    private static final String RECEIVER_PHONE_NUMBER = "+79991234567";
    private static final double TEMPERATURE_LIMIT = 20.0;
    private static final String ROUTEE_APP_ID = "appId";
    private static final String ROUTEE_APP_SECRET = "secret";

    private final RouteeSmsResource routeeSmsResource;
    private final TemperatureResource temperatureResource;
    private final RouteeAuthenticationService routeeAuthenticationService;

    /* IoC. */
    public TemperatureSmsNotificationServiceImpl(RouteeSmsResource routeeSmsResource,
                                                 TemperatureResource temperatureResource,
                                                 RouteeAuthenticationService routeeAuthenticationService) {
        this.routeeSmsResource = routeeSmsResource;
        this.temperatureResource = temperatureResource;
        this.routeeAuthenticationService = routeeAuthenticationService;
    }

    public void notifyBySms() {
        String token = routeeAuthenticationService.authenticate(ROUTEE_APP_ID, ROUTEE_APP_SECRET);
        double actualTemperature = temperatureResource.getTemperature(CITY_NAME);

        String temperature = (actualTemperature > TEMPERATURE_LIMIT ?
                HIGH_TEMPERATURE_MSG : LOW_TEMPERATURE_MSG);

        routeeSmsResource.sendSms(SmsRequestDto
                .builder()
                .from(SENDER_NAME)
                .accessToken(token)
                .receiverPhoneNumber(RECEIVER_PHONE_NUMBER)
                .body(temperature + actualTemperature)
                .build());
    }
}