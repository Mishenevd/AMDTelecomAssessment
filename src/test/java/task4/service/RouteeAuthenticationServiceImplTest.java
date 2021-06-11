package task4.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import task4.http.util.CredentialsBase64Encoder;
import task4.resource.RouteeAuthResource;

/**
 * Unit test for {@link RouteeAuthenticationServiceImpl}
 *
 * @author Dmitrii_Mishenev
 */
class RouteeAuthenticationServiceImplTest {
    private static final String ID = "id";
    private static final String SECRET = "secret";
    private static final String ENCODED_CREDS = "encodedIdAndSecret";
    private static final String EXPECTED_ACCESS_TOKEN = "accessToken";

    @Test
    void validIdAndSecret_authenticate_shouldReturnAccessToken() {

        RouteeAuthResource authResource = Mockito.mock(RouteeAuthResource.class);
        CredentialsBase64Encoder encoder = Mockito.mock(CredentialsBase64Encoder.class);
        RouteeAuthenticationServiceImpl authenticationService =
                new RouteeAuthenticationServiceImpl(authResource, encoder);
        Mockito.doReturn(ENCODED_CREDS).when(encoder).encodeCredentials(ID, SECRET);
        Mockito.doReturn(EXPECTED_ACCESS_TOKEN).when(authResource).authenticate(ENCODED_CREDS);

        String result = authenticationService.authenticate(ID, SECRET);

        assertEquals(EXPECTED_ACCESS_TOKEN, result);
    }
}