package task4.http;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Function;

class HttpFacadeImplTest {

    private Function<String, String> mockedBodyConverter = Mockito.mock(Function.class);
    private HttpBadResponseHandler<String> mockedBadResponseHandler = Mockito.mock(HttpBadResponseHandler.class);
    private HttpFacadeImpl<String> httpFacade = new HttpFacadeImpl<>(mockedBodyConverter, mockedBadResponseHandler);

    @Test
    void validRequest_exchange_convertedResult() {
        HttpRequest request = getHttpRequest();
        String mockedResponseBody = "responseBody";
        String expectedResponse = "convertedResponse";
        httpFacade = Mockito.spy(httpFacade);
        HttpResponse<String> mockedResponse = Mockito.mock(HttpResponse.class);
        doReturn(mockedResponse).when(httpFacade).doExchange(request);
        when(mockedBadResponseHandler.hasError(mockedResponse)).thenReturn(false);
        when(mockedResponse.body()).thenReturn(mockedResponseBody);
        when(mockedBodyConverter.apply(mockedResponseBody)).thenReturn(expectedResponse);

        String result = httpFacade.exchange(request);

        assertEquals(expectedResponse, result);
        Mockito.verify(httpFacade, times(1)).exchange(request);
        Mockito.verify(mockedBodyConverter).apply(mockedResponseBody);
        Mockito.verify(mockedBadResponseHandler).hasError(mockedResponse);
        Mockito.verifyNoMoreInteractions(mockedBadResponseHandler, mockedBodyConverter);
    }

    private HttpRequest getHttpRequest() {
        return HttpRequest
                .newBuilder(URI.create("http://localhost"))
                .build();
    }
}