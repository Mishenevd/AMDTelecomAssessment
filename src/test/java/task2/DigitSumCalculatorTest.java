package task2;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * Unit tests for {@link DigitSumCalculator}.
 *
 * @author Dmitrii_Mishenyov
 */
class DigitSumCalculatorTest {
    private final DigitSumCalculator digitSumCalculator = new DigitSumCalculator();

    @Test
    void oneDigitNumber_digitSum_shouldReturnSame() {
        int number = 3;

        int result = digitSumCalculator.digitSum(3);

        assertEquals(number, result, "Passed one-digit number. Argument itself should be returned");
    }

    @Test
    void minusThreeThousandHundredTwentyNine_digitSum_shouldReturnSix() {
        int number = -3129;

        int result = digitSumCalculator.digitSum(number);

        assertEquals(6, result, "Function should return six");
    }

    @Test
    void maxIntValue_digitSum_stackShouldNotOverflow() {
        int number = Integer.MAX_VALUE;

        Executable invocation = () -> digitSumCalculator.digitSum(number);

        assertDoesNotThrow(invocation, "Recursive function should not throw StackOverflowError");
    }
}