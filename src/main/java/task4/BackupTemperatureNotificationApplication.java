package task4;

import java.util.concurrent.TimeUnit;
import task4.resource.BackupTemperatureResourceImpl;
import task4.resource.RouteeResourceImpl;
import task4.scheluder.ScheduledTaskRunner;
import task4.service.RouteeAuthenticationServiceImpl;
import task4.service.TemperatureSmsNotificationService;
import task4.service.TemperatureSmsNotificationServiceImpl;

/**
 * Temperature notification application.
 * Capable to examine weather data and depending on the temperature send an sms message to a specified number.
 *
 * @author Dmitrii_Mishenev
 */
public class BackupTemperatureNotificationApplication {

    private static TemperatureSmsNotificationService temperatureSmsNotificationService;

    public static void main(String[] args) {
        final int executionTimes = 10;
        final int executionPeriod = 10;
        boolean isBackup = args[0].equals("Backup");

        temperatureSmsNotificationService = TemperatureSmsNotificationServiceImpl.getInstance(
                RouteeResourceImpl.getInstance(),
                BackupTemperatureResourceImpl.getInstance(),
                RouteeAuthenticationServiceImpl.getInstance());

        // Создаём шедулер
        ScheduledTaskRunner scheduledTaskRunner
                = new ScheduledTaskRunner(executionTimes, executionPeriod, TimeUnit.MINUTES);

        // Запускаем джобу с оповещениями о погоде
        scheduledTaskRunner.executeTask(temperatureSmsNotificationService::notifyBySms);
    }
}