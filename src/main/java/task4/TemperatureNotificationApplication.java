package task4;

import java.util.concurrent.TimeUnit;
import task4.resource.RouteeResourceImpl;
import task4.resource.TemperatureResourceImpl;
import task4.scheluder.ScheduledTaskRunner;
import task4.service.RouteeAuthenticationServiceImpl;
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

        // Создаём внешние ресурсы для HTTP вызовов.
        final RouteeResourceImpl routeeSmsResource = new RouteeResourceImpl();
        final TemperatureResourceImpl temperatureResource = new TemperatureResourceImpl();
        final RouteeAuthenticationServiceImpl routeeAuthenticationService = new RouteeAuthenticationServiceImpl();

        // Создаём сервис СМС оповещений о погоде в Греции
        TemperatureSmsNotificationServiceImpl temperatureSmsNotificationService
                = new TemperatureSmsNotificationServiceImpl();

        // Создаём шедулер
        ScheduledTaskRunner scheduledTaskRunner
                = new ScheduledTaskRunner(executionTimes, executionPeriod, TimeUnit.MINUTES);

        // Запускаем джобу с оповещениями о погоде в Греции
        scheduledTaskRunner.executeTask(temperatureSmsNotificationService::notifyBySms);
    }

    public static void reserve(String[] args) {
        final int executionTimes = 10;
        final int executionPeriod = 10;

        // Создаём сервис СМС оповещений о погоде в Греции
        TemperatureSmsNotificationServiceImpl temperatureSmsNotificationService
                = new TemperatureSmsNotificationServiceImpl();

        // Создаём шедулер
        ScheduledTaskRunner scheduledTaskRunner
                = new ScheduledTaskRunner(executionTimes, executionPeriod, TimeUnit.MINUTES);

        // Запускаем джобу с оповещениями о погоде в Греции
        scheduledTaskRunner.executeTask(temperatureSmsNotificationService::notifyBySms);
    }
}