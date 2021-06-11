package task4.http;

import static task4.logging.LoggerConfiguration.LOGGER;

import task4.exception.http.HttpRequestException;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Function;
import java.util.logging.Level;

/**
 * Provides convenient interface for HTTP communication.
 * Encapsulates low-level HTTP request composing/sending logic.
 * Retrieves information that client needs.
 * Gracefully handles unsuccessful HTTP responses.
 *
 * @author Dmitrii_Mishenev
 */
public class HttpFacadeImpl<T> implements HttpFacade<T> {
    private static final String HTTP_SEND_EXCEPTION = "Failure in sending HTTP request";

    private final HttpBadResponseHandler<String> badResponseHandler;
    private final Function<String, T> responseBodyConverter;

    /**
     * Executes HTTP request/retrieves needed data or handle unsuccessful HTTP response.
     * @param request HTTP request.
     * @return fetched information in client's needed format.
     */
    @Override
    public T exchange(HttpRequest request) {
        HttpResponse<String> response = doExchange(request);
        if (badResponseHandler.hasError(response)) {
            badResponseHandler.handleError(response);
        }
        LOGGER.log(Level.FINE, "exchange() - response:" + response);
        return responseBodyConverter.apply(response.body());
    }

    public HttpFacadeImpl(Function<String, T> responseBodyConverter,
                          HttpBadResponseHandler<String> badResponseHandler) {
        this.responseBodyConverter = responseBodyConverter;
        this.badResponseHandler = badResponseHandler;
    }

    /* Visible for testing */
    HttpResponse<String> doExchange(HttpRequest request) {
        try {
            return HttpClient.newHttpClient()
                    .send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            LOGGER.log(Level.WARNING, HTTP_SEND_EXCEPTION, e);
            throw new HttpRequestException(e);
        }
    }
}