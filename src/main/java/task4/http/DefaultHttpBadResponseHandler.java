package task4.http;

import task4.exception.http.HttpResponseException;

import java.net.http.HttpResponse;

/**
 * Default logic of bad HTTP response handling.
 *
 * @author Dmitrii_Mishenev
 */
public class DefaultHttpBadResponseHandler implements HttpBadResponseHandler<String> {

    /**
     * Determine whether HTTP response can be processed by this handler or not.
     * @param response processed HTTP response from server.
     * @return does this handler fit for processing passed response
     */
    @Override
    public boolean hasError(HttpResponse<String> response) {
        return response.statusCode() >= 300;
    }

    /**
     * Handle bad HTTP response.
     * @throws HttpResponseException exception with detalized information.
     * @param response processed HTTP response from server.
     */
    @Override
    public void handleError(HttpResponse<String> response) {
        throw  new HttpResponseException(response.statusCode(), response.body());
    }
}