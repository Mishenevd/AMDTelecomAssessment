package task4.exception.http;

/**
 * Exception thrown when error during HTTP response body parsing.
 *
 * @author Dmitrii_Mishenev
 */
public class HttpResponseBodyParseException extends RuntimeException {
    private static final String PARSE_EXCEPTION_MSG = "Can't parse http response";

    public HttpResponseBodyParseException() {
        super(PARSE_EXCEPTION_MSG);
    }
}