package task4.http.serialization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import task4.exception.http.HttpResponseBodyParseException;

/**
 * Unit test for {@link RouteeTokenParser}
 *
 * @author Dmitrii_Mishenev
 */
class RouteeTokenParserTest {
    private final RouteeTokenParser tokenParser = new RouteeTokenParser();

    @Test
    void validTokenResponse_apply_shouldParseToken() {
        String response = "{\"access_token\":\"12345\", \"token_type\":\"string\"," +
                " \"expires_in\":\"number\", \"scope\":\"string,string,string\",\"permissions\":[\"string\"]}";

        String result = tokenParser.apply(response);

        assertEquals(result, "12345");
    }

    @Test
    void invalidTokenResponse_apply_shouldThrowParsingException() {
        String response = "invalid response";

        Executable invocation = () -> tokenParser.apply(response);

        assertThrows(HttpResponseBodyParseException.class, invocation);
    }
}