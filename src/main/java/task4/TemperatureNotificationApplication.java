package task4;

import task4.iocframework.di.Cache;
import task4.iocframework.di.DIFramework;
import task4.service.TemperatureSmsNotificationServiceImpl;

/**
 * Temperature notification application.
 * Capable to examine weather data and depending on the temperature send an sms message to a specified number.
 *
 * @author Dmitrii_Mishenev
 */
public class TemperatureNotificationApplication {

    public static void main(String[] args) {
        Cache context = DIFramework.startApplication(TemperatureNotificationApplication.class);

        TemperatureSmsNotificationServiceImpl smsNotificationService =
                context.getBean(TemperatureSmsNotificationServiceImpl.class);

        System.out.println("Current temperature is: " + smsNotificationService
                .getTemperatureResource().getTemperature("no matter"));
    }
}