package task4.http;

import java.net.http.HttpResponse;

/**
 * Bad HTTP response handling.
 * Used in order to decouple business logic from handling unexpected bad HTTP responses.
 *
 * @author Dmitrii_Mishenev
 */
public interface HttpBadResponseHandler<T> {

    /**
     * Determine whether HTTP response can be processed by this handler or not.
     * @param response processed HTTP response from server.
     * @return does this handler fit for processing passed response
     */
    boolean hasError(HttpResponse<T> response);

    /**
     * Handle the error in the given response.
     * <p>This method is only called when {@link #hasError(HttpResponse)}
     * has returned {@code true}.
     * @param response the response with the error
     */
    void handleError(HttpResponse<T> response);
}