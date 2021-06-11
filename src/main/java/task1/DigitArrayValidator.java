package task1;

/**
 * Validator of digits array.
 *
 * @author Dmitrii_Mishenev
 */
public class DigitArrayValidator {

    /**
     * Validate digits array.
     * @param digits array of digits.
     * @return is array null or empty.
     */
    public static boolean isNullOrEmpty(int [] digits) {
        return (digits == null || digits.length == 0);
    }
}