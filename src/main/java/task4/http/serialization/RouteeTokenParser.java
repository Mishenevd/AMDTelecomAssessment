package task4.http.serialization;

import task4.exception.http.HttpResponseBodyParseException;

import java.util.Arrays;
import java.util.function.Function;

/**
 * Retrieves token from authentication response in Reoutee.
 *
 * @author Dmitrii_Mishenev
 */
public class RouteeTokenParser implements Function<String, String> {
    private static final String JSON_STRUCTURE_EXTRACTOR = "[:,{}]";
    private static final String QUOTES_DELIMITER = "\"";

    @Override
    public String apply(String jsonResponse) {
        return Arrays.stream(jsonResponse
                .split(QUOTES_DELIMITER))
                .filter((string) -> !string.matches(JSON_STRUCTURE_EXTRACTOR))
                .skip(1)
                .findFirst()
                .orElseThrow(HttpResponseBodyParseException::new);
    }
}