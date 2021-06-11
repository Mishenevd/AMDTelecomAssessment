package task3;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * SentenceChanger utility class.
 * Contains a function that change a sentence.
 *
 * @author Dmitrii_Mishenev
 */
public final class SentenceChanger {

    private static final String STARTS_WITH_VOWEL_REGEX = "[aeiouAEIOU].*";
    private static final String NON_LETTER_CHAR_REGEX = "[^a-zA-Z]";
    private static final String ANY_DELIMITERS_REGEX = "\\s+";
    private static final String WAY = "way";
    private static final String AY = "ay";

    /**
     * Method that takes a sentence of words and:
     * <br>Moves the first letter of each word to the end of the word.
     * <br>Adds "ay" to the end of the word.
     * <br>Words starting with a vowel simply have "way" appended to the end.
     * @param sentence Well-formed sentence with words of latin characters, digits and punctuation marks.
     *               Words can be separated by one or more space, tab or newline character.
     *               Non-words separated syntax elements (digits and punctuation marks) will not be considered a word.
     *               Words with inner hyphen symbol are acceptable (e.g. "off-street")
     * @return Corrected sentence.
     * @throws IllegalArgumentException - if malformed sentence/null/empty sentence were passed.
     */
    public String doRemake(String sentence) {

        SentenceValidator.validate(sentence);
        String[] words = sentence.split(ANY_DELIMITERS_REGEX);

        String formedSentence = Arrays.stream(words)
                .map(String::toLowerCase)
                .map(this::splitToWordAndPunctuation)
                .map(this::changeWord)
                .collect(Collectors.joining(" "));

        return formedSentence.substring(0, 1).toUpperCase() +
                formedSentence.substring(1);
    }

    private Pair<String, String> splitToWordAndPunctuation(String word) {
        int lastLetterPosition = word.length();
        StringBuilder punctuation = new StringBuilder();

        char[] wordSymbols = word.toCharArray();
        for (int i = word.length() - 1; i >= 0; i--) {
            if (String.valueOf(wordSymbols[i]).matches(NON_LETTER_CHAR_REGEX)) {
                lastLetterPosition --;
                punctuation.append(wordSymbols[i]);
            } else {
                break;
            }
        }
        return new Pair<>(word.substring(0, lastLetterPosition),
                punctuation.reverse().toString());
    }

    private String changeWord(Pair<String, String> wordAndPunctuation) {
        String word = wordAndPunctuation.getLeft();
        String punctuation = wordAndPunctuation.getRight();

        if (word.isEmpty() && !punctuation.isEmpty()) {
            return word + punctuation;
        }

        if (word.matches(STARTS_WITH_VOWEL_REGEX)) {
            return word + WAY + punctuation;
        }

        char firstLetter = word.charAt(0);
        return new StringBuilder(word)
                .deleteCharAt(0)
                .append(firstLetter)
                .append(AY)
                .append(punctuation)
                .toString();
    }
}