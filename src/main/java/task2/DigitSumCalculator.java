package task2;

/**
 * DigitSumCalculator utility class.
 * Contains a function that sums digits of number recursively until sum of digits < 9.
 *
 * @author Dmitrii_Mishenev
 */
public final class DigitSumCalculator {

    /**
     * Method that sums digits of number recursively until sum of digits < 9.
     * @param number original number.
     * @return sum of number's digits if the sum number is single. Otherwise sum new number recursively.
     *
     */
    public int digitSum(int number) {
        int sum = 0;

        while(number != 0) {
            sum += Math.abs(number % 10);
            number = number / 10;
        }
        return (sum > 9) ? digitSum(sum) : sum;
    }
}