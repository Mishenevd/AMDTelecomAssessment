package task4.exception.http;

/**
 * Exception thrown on unsuccessful HTTP response status code.
 *
 * @author Dmitrii_Mishenev
 */
public class HttpResponseException extends RuntimeException {
    private static final String EXCEPTION_MSG_TEMPLATE =
            "Unsuccessful HTTP request. Response code:%d, response body:%s";

    public HttpResponseException(int responseCode, String responseBody) {
        super(String.format(EXCEPTION_MSG_TEMPLATE, responseCode, responseBody));
    }
}