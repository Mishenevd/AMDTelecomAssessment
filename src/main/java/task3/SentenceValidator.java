package task3;

/**
 * Sentence validator.
 *
 * @author Dmitrii_Mishenev
 */
public class SentenceValidator {

    /**
     * Validate sentence.
     * @param sentence sentence to validate.
     * @throws IllegalArgumentException when the sentence is invalid.
     */
    public static void validate(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            throw new IllegalArgumentException("Null or empty string passed instead of well-formed sentence");
        }
    }
}