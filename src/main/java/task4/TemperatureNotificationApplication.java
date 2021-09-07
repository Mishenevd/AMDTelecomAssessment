package task4;

import java.util.concurrent.TimeUnit;
import task4.scheluder.ScheduledTaskRunner;
import task4.service.TemperatureSmsNotificationServiceImpl;

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

        // Создаём сервис СМС оповещений
        TemperatureSmsNotificationServiceImpl temperatureSmsNotificationService
                = new TemperatureSmsNotificationServiceImpl();

        // Создаём шедулер
        ScheduledTaskRunner scheduledTaskRunner
                = new ScheduledTaskRunner(executionTimes, executionPeriod, TimeUnit.MINUTES);

        // Запускаем джобу с оповещениями о погоде
        scheduledTaskRunner.executeTask(temperatureSmsNotificationService::notifyBySms);
    }
}