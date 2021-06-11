package task4.http.builder;

import task4.http.util.HttpRequestParamsExtractor;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Helper class for building URI with query parameters if needed.
 *
 * @author Dmitrii_Mishenev
 */
public class UriBuilder {
    private String spec;
    private final Map<String, String> parameters = new HashMap<>();

    public URI build() {
        String params = parameters.isEmpty() ? "" : HttpRequestParamsExtractor.extractHttpRequestParams(parameters);
        return  URI.create(spec + params);
    }

    public UriBuilder spec(String spec) {
        this.spec = spec;
        return this;
    }

    public UriBuilder param(String paramName, String paramValue) {
        this.parameters.put(paramName, paramValue);
        return this;
    }
}