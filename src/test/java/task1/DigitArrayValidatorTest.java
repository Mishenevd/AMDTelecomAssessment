package task1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link DigitArrayValidator}.
 *
 * @author Dmitrii_Mishenyov
 */
class DigitArrayValidatorTest {
    private final DigitArrayValidator digitArrayValidator = new DigitArrayValidator();

    @Test
    void null_isNullOrEmpty_shouldReturnTrue() {
        int[] digitArray = null;

        boolean result = digitArrayValidator.isNullOrEmpty(digitArray);

        assertTrue(result, "passed null instead of int array -> should return true.");
    }

    @Test
    void emptyArray_isNullOrEmpty_shouldReturnTrue() {
        int[] digitArray = {};

        boolean result = digitArrayValidator.isNullOrEmpty(digitArray);

        assertTrue(result, "passed empty int array -> should return true.");
    }

    @Test
    void notEmptyOrNullArray_isNullOrEmpty_shouldReturnFalse() {
        int[] digitArray = {1,2,3};

        boolean result = digitArrayValidator.isNullOrEmpty(digitArray);

        assertFalse(result, "passed non null/ non empty int array -> should return false.");
    }
}