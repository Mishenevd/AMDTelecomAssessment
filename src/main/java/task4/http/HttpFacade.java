package task4.http;

import java.net.http.HttpRequest;

/**
 * Provides convenient interface for HTTP communication.
 * Encapsulates low-level HTTP request composing/sending logic.
 * Retrieves information that client needs.
 * Gracefully handles unsuccessful HTTP responses.
 *
 * @author Dmitrii_Mishenev
 */
public interface HttpFacade<T> {

    /**
     * Executes HTTP request/retrieves needed data or handle unsuccessful HTTP response.
     * @param request ready to send HTTP request.
     * @return fetched information in client's needed format.
     */
    T exchange(HttpRequest request);
}