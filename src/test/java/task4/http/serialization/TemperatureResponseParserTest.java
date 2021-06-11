package task4.http.serialization;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit test for {@link TemperatureResponseParser}
 *
 * @author Dmitrii_Mishenev
 */
class TemperatureResponseParserTest {
    private final  TemperatureResponseParser temperatureResponseParser = new TemperatureResponseParser();

    @Test
    void validJsonResponse_apply_shouldParseTemperatureAsDouble() {
        String jsonResponse = "{\n" +
                "        \"temp\": 19.22,\n" +
                "        \"feels_like\": 19.23,\n" +
                "        \"temp_min\": 17.33,\n" +
                "        \"temp_max\": 20.47,\n" +
                "        \"pressure\": 1012,\n" +
                "        \"humidity\": 78\n" +
                "    }";

        double result = temperatureResponseParser.apply(jsonResponse);

        assertEquals(19.22, result);
    }
}