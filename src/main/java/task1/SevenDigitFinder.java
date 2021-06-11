package task1;

import java.util.Arrays;

/**
 * SevenDigitFinder utility class.
 * Contains a function that determines the occurrence of the digit "7" in an array of numbers.
 *
 * @author Dmitrii_Mishenyov
 */
public final class SevenDigitFinder {
    private static final String FOUND = "Found";
    private static final String NOT_FOUND = "there is no 7 in the array";

    /**
     * Method that determines the occurrence of the digit "7" in an array of numbers.
     * @param numbers integer numbers array.
     * @return string that represents finding result.
     */
    public String findSeven(int[] numbers) {
        return !DigitArrayValidator.isNullOrEmpty(numbers)
                && Arrays.stream(numbers).anyMatch(this::containsDigitSeven)
                ? FOUND
                : NOT_FOUND;
    }

    private boolean containsDigitSeven(int number) {
        while(number != 0) {
            if(Math.abs(number % 10) == 7) {
                return true;
            }
            number = number / 10;
        }
        return false;
    }
}