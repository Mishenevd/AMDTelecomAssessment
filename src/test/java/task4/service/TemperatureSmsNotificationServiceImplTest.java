package task4.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import task4.dto.SmsRequestDto;
import task4.resource.RouteeSmsResource;
import task4.resource.TemperatureResource;

/**
 * Unit test for {@link TemperatureSmsNotificationServiceImpl}
 *
 * @author Dmitrii_Mishenev
 */
class TemperatureSmsNotificationServiceImplTest {

    @Test
    void notificationCalled_notifyBySms_shouldNotifyUserBySms() {
        final RouteeSmsResource routeeSmsResource = Mockito.mock(RouteeSmsResource.class);
        final TemperatureResource temperatureResource = Mockito.mock(TemperatureResource.class);
        final RouteeAuthenticationService routeeAuthenticationService = Mockito.mock(RouteeAuthenticationService.class);
        final TemperatureSmsNotificationService notificationService = new TemperatureSmsNotificationServiceImpl(routeeSmsResource,
                temperatureResource, routeeAuthenticationService);
        final double temperature = 19.0;
        Mockito.when(temperatureResource.getTemperature(anyString())).thenReturn(temperature);

        notificationService.notifyBySms();

        Mockito.verify(temperatureResource).getTemperature(anyString());
        Mockito.verify(routeeAuthenticationService).authenticate(anyString(), anyString());
        Mockito.verify(routeeSmsResource).sendSms(any(SmsRequestDto.class));
    }
}