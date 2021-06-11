package task3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link SentenceChanger}.
 *
 * @author Dmitrii_Mishenyov
 */
class SentenceChangerTest {
    private final SentenceChanger sentenceChanger = new SentenceChanger();

    @Test
    void wellFormedSentence_doRemake_shouldReceivePunctuation() {
        String sentence = "Cats are great pets.";

        String result = sentenceChanger.doRemake("Cats are great pets.");

        assertEquals("Atscay areway reatgay etspay.", result);
    }

    @Test
    void sentenceWithCharacters_doRemake_shouldNotReplaceCharacters() {
       String sentence = "Tom !)))32 got a small piece of pie.";

       String result = sentenceChanger.doRemake(sentence);

       assertEquals("Omtay !)))32 otgay away mallsay iecepay ofway iepay.", result);
    }

    @Test
    void differentSeparators_doRemake_shouldHandleSeparators() {
        String sentence = "Cats   are  great \n pets.";

        String result = sentenceChanger.doRemake(sentence);

        assertEquals("Atscay areway reatgay etspay.", result);
    }

    @Test
    void manyPunctuationMarks_doRemake_shouldSavePunctuation() {
        String sentence = "Cats are great pets...";

        String result = sentenceChanger.doRemake(sentence);

        assertEquals("Atscay areway reatgay etspay...", result);
    }

    @Test
    void wordsWithHypen_doRemake_shouldSaveHyphen() {
        String sentence = "He told us - a very-very exciting tale.";

        String result = sentenceChanger.doRemake(sentence);

        assertEquals("Ehay oldtay usway - away ery-veryvay excitingway aletay.", result);
    }
}