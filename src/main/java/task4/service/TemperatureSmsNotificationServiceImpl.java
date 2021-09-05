package task4.service;

import task4.dto.SmsRequestDto;
import task4.resource.RouteeResourceImpl;
import task4.resource.TemperatureResourceImpl;

/**
 * Service for checking actual temperature and sending SMS with temperature.
 *
 * @author Dmitrii_Mishenev
 */
public class TemperatureSmsNotificationServiceImpl {
    private static final String CITY_NAME = "Thessaloniki";
    private static final String SENDER_NAME = "Dmitriy Mishenyov";
    private static final String LOW_TEMPERATURE_MSG = "Temperature less than 20C. ";
    private static final String HIGH_TEMPERATURE_MSG = "Temperature more than 20C. ";
    private static final String RECEIVER_PHONE_NUMBER = "+306978745957";
    private static final double TEMPERATURE_LIMIT = 20.0;
    private static final String ROUTEE_APP_ID = "appId";
    private static final String ROUTEE_APP_SECRET = "secret";

    private final RouteeResourceImpl routeeSmsResource;
    private final TemperatureResourceImpl temperatureResource;
    private final RouteeAuthenticationServiceImpl routeeAuthenticationService;

    /* 1. Сервис заточен на работу с конкретными имплементациями ресурсов,
     нельзя подменить без нарушения OCP. */
    public TemperatureSmsNotificationServiceImpl() {
        this.routeeSmsResource = new RouteeResourceImpl();
        this.temperatureResource = new TemperatureResourceImpl();
        this.routeeAuthenticationService = new RouteeAuthenticationServiceImpl();
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