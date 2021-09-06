package task4;

import java.util.concurrent.TimeUnit;
import task4.resource.BackupTemperatureResourceImpl;
import task4.resource.RouteeResourceImpl;
import task4.resource.RouteeSmsResource;
import task4.resource.TemperatureResource;
import task4.resource.TemperatureResourceImpl;
import task4.scheluder.ScheduledTaskRunner;
import task4.service.RouteeAuthenticationService;
import task4.service.RouteeAuthenticationServiceImpl;
import task4.service.TemperatureSmsNotificationService;
import task4.service.TemperatureSmsNotificationServiceImpl;

/**
 * Temperature notification application.
 * Capable to examine weather data and depending on the temperature send an sms message to a specified number.
 *
 * @author Dmitrii_Mishenev
 */
public class TemperatureNotificationApplication {

    private static TemperatureSmsNotificationService temperatureSmsNotificationService;

    public static void main(String[] args) {
        final int executionTimes = 10;
        final int executionPeriod = 10;
        boolean isBackup = args[0].equals("Backup");

        // Создаём внешние ресурсы для HTTP вызовов.
        final RouteeSmsResource routeeSmsResource = new RouteeResourceImpl();
        final TemperatureResource temperatureResource = isBackup ?
                new BackupTemperatureResourceImpl() : new TemperatureResourceImpl();
        final RouteeAuthenticationService routeeAuthenticationService =
                new RouteeAuthenticationServiceImpl();

        /*
        * Поскольку классы могут обращаться друг к другу опосредованно
        * через интерфейсы и абстрактные классы, механизм создания объектов тоже изменится.
        * Кто будет ответственнен за создание & управление объектами?
        *  Для создания объектов потребуется применением таких шаблонов проектирования
        * как «Фабрика» и «Фабричный метод», либо через DI
        * */
        temperatureSmsNotificationService = new TemperatureSmsNotificationServiceImpl(routeeSmsResource,
                temperatureResource, routeeAuthenticationService);

        // Создаём шедулер
        ScheduledTaskRunner scheduledTaskRunner
                = new ScheduledTaskRunner(executionTimes, executionPeriod, TimeUnit.MINUTES);

        // Запускаем джобу с оповещениями о погоде в Греции
        scheduledTaskRunner.executeTask(temperatureSmsNotificationService::notifyBySms);
    }
}