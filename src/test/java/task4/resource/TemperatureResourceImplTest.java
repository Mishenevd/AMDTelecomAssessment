package task4.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import task4.http.HttpFacade;

/**
 * Unit test for {@link TemperatureResourceImpl}
 *
 * @author Dmitrii_Mishenev
 */
class TemperatureResourceImplTest {
    private final HttpFacade<Double> httpFacade = Mockito.mock(HttpFacade.class);
    private final TemperatureResource temperatureResource = new TemperatureResourceImpl(httpFacade);

    @Test
    void cityNameProvided_getTemperature_shouldReturnTemperature() {
        double expectedResult = 22.0;
        Mockito.when(httpFacade.exchange(any())).thenReturn(expectedResult);

        double result = temperatureResource.getTemperature("City");

        assertEquals(expectedResult, result);
    }
}