package task4.exception.http;

/**
 * Exception thrown when HTTP request can't be sent.
 *
 * @author Dmitrii_Mishenev
 */
public class HttpRequestException extends RuntimeException {
    private static final String DEFAULT_HTTP_SEND_ERROR_MSG = "Can't send http request. Cause: ";

    public HttpRequestException(Throwable cause) {
        super(DEFAULT_HTTP_SEND_ERROR_MSG, cause);
    }
}