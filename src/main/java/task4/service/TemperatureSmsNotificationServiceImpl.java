package task4.service;

import task4.dto.SmsRequestDto;
import task4.resource.RouteeResourceImpl;
import task4.resource.RouteeSmsResource;
import task4.resource.TemperatureResource;
import task4.resource.TemperatureResourceImpl;

/**
 * Service for checking actual temperature and sending SMS with temperature.
 *
 * @author Dmitrii_Mishenev
 */
public class TemperatureSmsNotificationServiceImpl implements TemperatureSmsNotificationService {
    private static final String CITY_NAME = "Thessaloniki";
    private static final String SENDER_NAME = "MishenevD";
    private static final String LOW_TEMPERATURE_MSG = "Temperature less than 20C. ";
    private static final String HIGH_TEMPERATURE_MSG = "Temperature more than 20C. ";
    private static final String RECEIVER_PHONE_NUMBER = "+306978745957";
    private static final double TEMPERATURE_LIMIT = 20.0;
    private static final String ROUTEE_APP_ID = "5f9138288b71de3617a87cd3";
    private static final String ROUTEE_APP_SECRET = "RSj69jLowJ";

    private final RouteeSmsResource routeeSmsResource;
    private final TemperatureResource temperatureResource;
    private final RouteeAuthenticationService routeeAuthenticationService;

    public TemperatureSmsNotificationServiceImpl(RouteeSmsResource routeeSmsResource,
                                                 TemperatureResource temperatureResource,
                                                 RouteeAuthenticationService routeeAuthenticationService) {
        this.routeeSmsResource = routeeSmsResource;
        this.temperatureResource = temperatureResource;
        this.routeeAuthenticationService = routeeAuthenticationService;
    }

    public TemperatureSmsNotificationServiceImpl() {
        temperatureResource = new TemperatureResourceImpl();
        routeeSmsResource = new RouteeResourceImpl();
        routeeAuthenticationService = new RouteeAuthenticationServiceImpl();
    }

    @Override
    public void notifyBySms() {
        String token = routeeAuthenticationService.authenticate(ROUTEE_APP_ID, ROUTEE_APP_SECRET);
        double actualTemperature = temperatureResource.getTemperature(CITY_NAME);

        String temperature = (actualTemperature > TEMPERATURE_LIMIT ? HIGH_TEMPERATURE_MSG : LOW_TEMPERATURE_MSG);
        routeeSmsResource.sendSms(SmsRequestDto
                .builder()
                .from(SENDER_NAME)
                .accessToken(token)
                .receiverPhoneNumber(RECEIVER_PHONE_NUMBER)
                .body(temperature + actualTemperature)
                .build());
    }
}