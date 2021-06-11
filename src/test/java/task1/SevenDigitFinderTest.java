package task1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link SevenDigitFinder}.
 *
 * @author Dmitrii_Mishenyov
 */
class SevenDigitFinderTest {
    private static final String FOUND = "Found";
    private static final String NOT_FOUND = "there is no 7 in the array";

    private final SevenDigitFinder sevenDigitFinder = new SevenDigitFinder();

    @Test
    void positiveNumbersWithSeven_findSeven_shouldReturnFoundMessage() {
        int[] numbers = {1,2,3,4,5,6,7,8};

        String result = sevenDigitFinder.findSeven(numbers);

        assertEquals(FOUND, result, "\"7\" digit contains in positive numbers array -> should be found");
    }

    @Test
    void positivePolyDigitNumbersWithSeven_findSeven_shouldReturnFoundMessage() {
        int[] numbers = {321,12,2,3,4,5,6,1272,8};

        String result = sevenDigitFinder.findSeven(numbers);

        assertEquals(FOUND, result, "\"7\" digit contains in positive poly digit numbers array -> should be found");
    }

    @Test
    void negativePolyDigitNumbers_findSeven_shouldReturnFoundMessage() {
        int[] numbers = {-321,-12,-2,-3,-4,-5,-6,-1272,-8};

        String result = sevenDigitFinder.findSeven(numbers);

        assertEquals(FOUND, result, "\"7\" digit contains in negative poly digit numbers array -> should be found");
    }

    @Test
    void positiveNumbersWithoutSeven_findSeven_shouldReturnNotFoundMessage() {
        int[] numbers = {1,2,3,4,5,6,1,8};

        String result = sevenDigitFinder.findSeven(numbers);

        assertEquals(NOT_FOUND, result, "\"7\" digit does not contain in positive numbers array -> should not be found");
    }

    @Test
    void positivePolyDigitNumbersWithoutSeven_findSeven_shouldReturnNotFoundMessage() {
        int[] numbers = {123,241,899,666,125,12,11,82};

        String result = sevenDigitFinder.findSeven(numbers);

        assertEquals(NOT_FOUND, result, "\"7\" digit does not contain in positive poly digit numbers array -> should not be found");
    }

    @Test
    void negativeNumbersWithoutSeven_findSeven_shouldReturnNotFoundMessage() {
        int[] numbers = {-3,-1,-2,-3,-4,-5,-6,-9,-8};

        String result = sevenDigitFinder.findSeven(numbers);

        assertEquals(NOT_FOUND, result, "\"7\" digit does not contain in negative numbers array -> should not be found");
    }

    @Test
    void negativePolyDigitNumbersWithoutSeven_findSeven_shouldReturnNotFoundMessage() {
        int[] numbers = {-3,-1,-2,-3,-4,-5,-6,-9,-8};

        String result = sevenDigitFinder.findSeven(numbers);

        assertEquals(NOT_FOUND, result, "\"7\" digit does not contain in negative poly digit numbers array -> should not be found");
    }

    @Test
    void null_findSeven_shouldReturnNotFoundMessage() {
        int[] numbers = null;

        String result = sevenDigitFinder.findSeven(numbers);

        assertEquals(NOT_FOUND, result, "\"7\" digit does not contain in null -> should not be found");
    }

    @Test
    void emptyArray_findSeven_shouldReturnNotFoundMessage() {
        int[] numbers = {};

        String result = sevenDigitFinder.findSeven(numbers);

        assertEquals(NOT_FOUND, result, "\"7\" digit does not contain in empty numbers array -> should not be found");
    }
}