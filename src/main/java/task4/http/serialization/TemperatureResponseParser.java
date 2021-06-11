package task4.http.serialization;

import task4.exception.http.HttpResponseBodyParseException;

import java.util.Arrays;
import java.util.function.Function;

/**
 * Parse current temperature from OpenWeather service.
 *
 * @author Dmitrii_Mishenev
 */
public class TemperatureResponseParser implements Function<String, Double> {
    private static final String JSON_STRUCTURE_EXTRACTOR = "[{,]";
    private static final String TEMPERATURE_FIELD_EXTRACTOR = "\"temp\"";
    private static final String DIGIT_EXTRACTOR = "[^0-9.]";

    @Override
    public Double apply(String jsonResponse) {
        return Arrays.stream(jsonResponse
                .split(JSON_STRUCTURE_EXTRACTOR))
                .filter(s -> s.contains(TEMPERATURE_FIELD_EXTRACTOR))
                .map(s -> s.replaceAll(DIGIT_EXTRACTOR, ""))
                .findAny()
                .map(Double::valueOf)
                .orElseThrow(HttpResponseBodyParseException::new);
    }
}