package task4.http;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.http.HttpResponse;

class DefaultHttpBadResponseHandlerTest {
    DefaultHttpBadResponseHandler defaultHttpBadResponseHandler = new DefaultHttpBadResponseHandler();

    @Test
    void twoHundredStatusResponse_hasError_shouldReturnFalse() {
        HttpResponse<String> mockedHttpResponse = Mockito.mock(HttpResponse.class);
        Mockito.when(mockedHttpResponse.statusCode()).thenReturn(200);

        boolean result = defaultHttpBadResponseHandler.hasError(mockedHttpResponse);

        assertFalse(result, "200 status code -> no error");
    }

    @Test
    void fourHundredStatusResponse_hasError_shouldReturnFalse() {
        HttpResponse<String> mockedHttpResponse = Mockito.mock(HttpResponse.class);
        Mockito.when(mockedHttpResponse.statusCode()).thenReturn(400);

        boolean result = defaultHttpBadResponseHandler.hasError(mockedHttpResponse);

        assertTrue(result, "400 status code -> client error");
    }

    @Test
    void fiveHundredStatusResponse_hasError_shouldReturnFalse() {
        HttpResponse<String> mockedHttpResponse = Mockito.mock(HttpResponse.class);
        Mockito.when(mockedHttpResponse.statusCode()).thenReturn(500);

        boolean result = defaultHttpBadResponseHandler.hasError(mockedHttpResponse);

        assertTrue(result, "500 status code -> server error");
    }
}