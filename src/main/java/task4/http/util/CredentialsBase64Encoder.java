package task4.http.util;

import java.util.Base64;

/**
 * Helper class for encoding credentials in base64 format.
 *
 * @author Dmitrii_Mishenev
 */
public class CredentialsBase64Encoder {

    public String encodeCredentials(String id, String secret) {
        return Base64.getEncoder().encodeToString((id + ":" + secret).getBytes());
    }
}