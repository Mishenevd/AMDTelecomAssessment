package task4.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import task4.dto.SmsRequestDto;
import task4.http.HttpFacade;
import task4.http.serialization.SmsRequestSerializer;

import java.net.http.HttpRequest;

/**
 * Unit test for {@link RouteeResourceImpl}
 *
 * @author Dmitrii_Mishenev
 */
class RouteeResourceImplTest {
    private final HttpFacade<String> smsFacade = Mockito.mock(HttpFacade.class);
    private final HttpFacade<String> authenticationFacade = Mockito.mock(HttpFacade.class);
    private final SmsRequestSerializer smsRequestSerializer = Mockito.mock(SmsRequestSerializer.class);
    private final RouteeResourceImpl routeeResource =
            new RouteeResourceImpl(smsFacade, authenticationFacade, smsRequestSerializer);

    @Test
    void validCredentials_authenticate_shouldReturnAccessToken() {
        String credentials = "123";
        String expectedResponseToken = "456";
        Mockito.doReturn(expectedResponseToken).when(authenticationFacade).exchange(any(HttpRequest.class));

        String result = routeeResource.authenticate(credentials);

        Mockito.verify(authenticationFacade).exchange(any());
        assertEquals(expectedResponseToken, result);
    }

    @Test
    void validSmsDto_sendSms_shouldSendSms() {
        SmsRequestDto dto = SmsRequestDto
                .builder()
                .receiverPhoneNumber("123")
                .from("Dmitrii")
                .accessToken("12345")
                .build();
        Mockito.when(smsRequestSerializer.serializeBody(dto)).thenReturn("value");

        routeeResource.sendSms(dto);

        Mockito.verify(smsRequestSerializer).serializeBody(dto);
        Mockito.verify(smsFacade).exchange(any());
    }
}