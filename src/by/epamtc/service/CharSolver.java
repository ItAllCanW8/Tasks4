package by.epamtc.service;

import by.epamtc.util.DataValidator;

public class CharSolver {
    public static final char   SPACE = ' ';
    public static final char[] PUNCTUATION_MARKS = {',', '.', '!', '?', ':', '-', ';'};
    public static final char   WRONG_LETTER = 'A';
    public static final char   WRONG_LETTER_PREFIX = 'P';
    public static final char   DESIRED_LETTER = 'O';
    public static final char[] CONSONANTS = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r',
            's', 't', 'v', 'w', 'x', 'y', 'z'};

    public static String replaceLetters(String text, int k, char symbol){
        DataValidator.checkForNull(text);
        DataValidator.checkBounds(k);

        char[] result = text.toCharArray();

        int spaceIndex = -1;
        int wordLength = 0;

        for (int i = 0; i < result.length; i++) {
            if (!isPunctuationMark(result[i]) && result[i] != SPACE)
                wordLength++;

            if (result[i] == SPACE || i == result.length - 1) {
                if (wordLength >= k)
                    result[spaceIndex + k] = symbol;

                wordLength = 0;
                spaceIndex = i;
            }
        }

        return new String(result);
    }

    public static String replaceWrongLetter(String text){
        DataValidator.checkForNull(text);

        char[] result = text.toCharArray();

        int spaceIndex = -1;
        int wordLength = 0;

        for (int i = 0; i < result.length; i++) {
            if (!isPunctuationMark(result[i]) && result[i] != SPACE)
                wordLength++;

            if (result[i] == SPACE || i == result.length - 1) {
                if (result[spaceIndex + wordLength] == WRONG_LETTER && result[spaceIndex + wordLength - 1]
                        == WRONG_LETTER_PREFIX)
                    result[spaceIndex + wordLength] = DESIRED_LETTER;

                spaceIndex = i;
                wordLength = 0;
            }
        }

        return new String(result);
    }

    public static String replaceSubstring(String text, String substring, int length){
        DataValidator.checkForNull(text);
        DataValidator.checkBounds(length);

        char[] result = text.toCharArray();
        int spaceIndex = -1;
        int wordLength = 0;

        for (int i = 0; i < result.length; i++) {
            if (!isPunctuationMark(result[i]) && result[i] != SPACE)
                wordLength++;

            if (result[i] == SPACE || i == result.length - 1) {
                if (wordLength == length) {
                    char[] temp = new char[result.length + substring.length() - wordLength];

                    System.arraycopy(result, 0, temp, 0, spaceIndex + 1);

                    char[] substringArr = substring.toCharArray();
                    int tempIndex = spaceIndex + 1;

                    for (char c : substringArr) {
                        temp[tempIndex] = c;
                        tempIndex++;
                    }

                    for (int j = spaceIndex + wordLength + 1; j < result.length; j++) {
                        temp[tempIndex] = result[j];
                        tempIndex++;
                    }

                    result = temp;
                }

                spaceIndex = i;
                wordLength = 0;
            }
        }

        return new String(result);
    }

    public static String removeRedundantCharacters(String text){
        DataValidator.checkForNull(text);

        char[] result = text.toCharArray();
        int charsCounter = -1;

        for (int i = 0; i < result.length; i++) {
            if (isPunctuationMark(result[i])) {
                result[i] = 0;
                charsCounter++;
            }
        }

        return toString(result, charsCounter);
    }

    public static String removeConsWords(String text, int length){
        DataValidator.checkForNull(text);
        DataValidator.checkBounds(length);

        char[] result = text.toCharArray();

        int wordLength = 0;
        int charsCounter = 0;
        int spaceIndex = -1;

        for (int i = 0; i < result.length; i++) {
            if (!isPunctuationMark(result[i]) && result[i] != SPACE)
                wordLength++;

            if (result[i] == SPACE || i == result.length - 1) {
                if (wordLength == length && isConsonant(result[spaceIndex + 1])) {
                    for (int j = spaceIndex + 1; j < spaceIndex + wordLength + 1; j++) {
                        result[j] = 0;
                        charsCounter++;
                    }
                }

                spaceIndex = i;
                wordLength = 0;
            }
        }

        return toString(result, charsCounter);
    }

    private static String toString(char[] chars, int charsCounter) {
        char[] result = new char[chars.length - charsCounter];

        int j = 0;

        for (char aChar : chars) {
            if (aChar != 0) {
                result[j] = aChar;
                j++;
            }
        }

        return new String(result);
    }

    private static boolean isPunctuationMark(char character) {
        for (char punctuationMark : PUNCTUATION_MARKS)
            if (punctuationMark == character)
                return true;

        return false;
    }

    private static boolean isConsonant(char letter) {
        for (char consonantLetter : CONSONANTS)
            if (consonantLetter == letter || ((char) (consonantLetter - 32) == letter))
                return true;

        return false;
    }
}
