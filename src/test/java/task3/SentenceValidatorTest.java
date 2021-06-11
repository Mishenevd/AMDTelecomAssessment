package task3;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * Unit tests for {@link SentenceValidator}
 */
class SentenceValidatorTest {
    private SentenceValidator sentenceValidator = new SentenceValidator();

    @Test
    void null_validate_shouldThrowException() {
        String sentence = null;

        Executable executedCode = () -> sentenceValidator.validate(sentence);

        assertThrows(IllegalArgumentException.class, executedCode);
    }

    @Test
    void emptyString_validate_shouldThrowException() {
        String sentence = "";

        Executable executedCode = () -> sentenceValidator.validate(sentence);

        assertThrows(IllegalArgumentException.class, executedCode);
    }

    @Test
    void string_validate_shouldNotThrowException() {
        String sentence = "Simple sentence.";

        Executable executedCode = () -> sentenceValidator.validate(sentence);

        assertDoesNotThrow(executedCode);
    }
}