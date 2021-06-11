package task4;

import task4.scheluder.ScheduledTaskRunner;
import task4.service.TemperatureSmsNotificationService;
import task4.service.TemperatureSmsNotificationServiceImpl;

import java.util.concurrent.TimeUnit;

/**
 * Temperature notification application.
 * Capable to examine weather data and depending on the temperature send an sms message to a specified number.
 *
 * @author Dmitrii_Mishenev
 */
public class TemperatureNotificationApplication {

    public static void main(String[] args) {
        final int executionTimes = 10;
        final int executionPeriod = 10;

        ScheduledTaskRunner scheduledTaskRunner = new ScheduledTaskRunner(executionTimes, executionPeriod, TimeUnit.MINUTES);
        TemperatureSmsNotificationService temperatureSmsNotificationService = new TemperatureSmsNotificationServiceImpl();

        scheduledTaskRunner.executeTask(temperatureSmsNotificationService::notifyBySms);
    }
}