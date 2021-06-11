package task4.http.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Unit test for {@link HttpRequestParamsExtractor}
 *
 * @author Dmitrii_Mishenev
 */
class HttpRequestParamsExtractorTest {

    @Test
    void notEmptyParamsMap_extractHttpRequestParams_shouldReturnParamsString() {
        Map<String,String> paramsMap = new LinkedHashMap<>();
        paramsMap.put("k1", "v1");
        paramsMap.put("k2", "v2");

        String result = HttpRequestParamsExtractor.extractHttpRequestParams(paramsMap);

        assertEquals("?k1=v1&k2=v2", result);
    }

    @Test
    void emptyParamsMap_extractHttpRequestParams_shouldReturnEmptyString() {
        Map<String,String> paramsMap = Collections.EMPTY_MAP;

        String result = HttpRequestParamsExtractor.extractHttpRequestParams(paramsMap);

        assertEquals("", result);
    }
}