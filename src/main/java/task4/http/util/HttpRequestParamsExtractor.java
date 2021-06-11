package task4.http.util;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Helper class for fetching HTTP request parameters.
 *
 * @author Dmitrii_Mishenev
 */
public class HttpRequestParamsExtractor {

    /**
     * Creates URI parameters string from key value parameters pairs.
     * @param requestParamsMap parameters in key-value format.
     * @return ready-to-use parameters string.
     */
    public static String extractHttpRequestParams(Map<String,String> requestParamsMap) {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : requestParamsMap.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
            result.append("&");
        }
        String resultString = result.toString();
        return resultString.length() > 0
                ? "?" + resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}