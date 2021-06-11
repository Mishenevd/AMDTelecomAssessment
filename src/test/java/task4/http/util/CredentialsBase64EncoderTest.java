package task4.http.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Base64;

/**
 * Unit test for {@link CredentialsBase64Encoder}
 *
 * @author Dmitrii_Mishenev
 */
class CredentialsBase64EncoderTest {
    private final CredentialsBase64Encoder encoder = new CredentialsBase64Encoder();

    @Test
    void idAndPassword_encodeCredentials_shouldEncodeWithDelimiter() {
        String id = "id";
        String password = "password";
        String expected = Base64.getEncoder().encodeToString((id + ":" + password).getBytes());

        String result = encoder.encodeCredentials(id, password);

        assertEquals(expected, result);
    }
}