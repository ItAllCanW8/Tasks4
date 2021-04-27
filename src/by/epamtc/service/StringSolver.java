package by.epamtc.service;

import by.epamtc.util.DataValidator;

public class StringSolver {
    public static final String   SPACE = " ";
    public static final String[] PUNCTUATION_MARKS = {",", ".", "!", "?", ":", "-", ";"};
    public static final char     WRONG_LETTER = 'A';
    public static final char     WRONG_LETTER_PREFIX = 'P';
    public static final char     DESIRED_LETTER = 'O';
    public static final String[] CONSONANTS = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r",
            "s", "t", "v", "w", "x", "y", "z"};

    public static String replaceLetters(String text, int k, String symbol) {
        DataValidator.checkForNull(text);
        DataValidator.checkBounds(k);

        String[] splittedText = splitText(text);

        for (int i = 0; i < splittedText.length; i++) {
            StringBuilder word = new StringBuilder(splittedText[i]);

            int wordLength = word.length();

            if (isPunctuationMark(word.substring(wordLength - 1, wordLength)))
                wordLength--;

            if (k <= wordLength)
                splittedText[i] = word.replace(k - 1, k, symbol).toString();
        }

        return toString(splittedText);
    }

    public static String replaceWrongLetter(String text) {
        DataValidator.checkForNull(text);

        String[] splittedText = splitText(text);

        for (int i = 0; i < splittedText.length; i++) {
            String word = splittedText[i];
            int wordLength = word.length();

            if (isPunctuationMark(word.substring(wordLength - 1, wordLength)))
                wordLength--;

            if (word.charAt(wordLength - 1) == WRONG_LETTER && word.charAt(wordLength - 2) == WRONG_LETTER_PREFIX) {
                word = word.replace(WRONG_LETTER, DESIRED_LETTER);
                splittedText[i] = word;
            }
        }

        return toString(splittedText);
    }

    public static String replaceSubstring(String text, String substring, int length) {
        DataValidator.checkForNull(text);
        DataValidator.checkBounds(length);

        String[] splittedText = splitText(text);

        for (int i = 0; i < splittedText.length; i++) {
            StringBuilder word = new StringBuilder(splittedText[i]);
            int wordLength = word.length();

            String punctuationMark = "";

            if (isPunctuationMark(word.substring(wordLength - 1, wordLength))) {
                punctuationMark = word.substring(wordLength - 1);
                wordLength--;
            }

            if (wordLength == length)
                splittedText[i] = substring + punctuationMark;
        }

        return toString(splittedText);
    }

    public static String removeRedundantCharacters(String text) {
        DataValidator.checkForNull(text);

        String[] splittedText = splitText(text);

        for (int i = 0; i < splittedText.length; i++) {
            StringBuilder word = new StringBuilder(splittedText[i]);
            int wordLength = word.length();

            if (isPunctuationMark(word.substring(wordLength - 1, wordLength))) {
                word.deleteCharAt(wordLength - 1);
                splittedText[i] = word.toString();
            }
        }

        return toString(splittedText);
    }

    public static String removeConsWords(String text, int length) {
        DataValidator.checkForNull(text);
        DataValidator.checkBounds(length);

        String[] splittedText = splitText(text);

        for (int i = 0; i < splittedText.length; i++) {
            StringBuilder word = new StringBuilder(splittedText[i]);
            int wordLength = word.length();

            String punctuationMark = "";

            if (isPunctuationMark(word.substring(wordLength - 1, wordLength))) {
                punctuationMark = String.valueOf(word.charAt(wordLength - 1));
                wordLength--;
            }

            if (wordLength == length && isConsonant(word.substring(0, 1)))
                splittedText[i] = punctuationMark;
        }

        return toString(splittedText);
    }

    private static String[] splitText(String text) {
        text = text.trim();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            if (i != text.length() - 1 || i != 0 )
                if (text.substring(i, i + 1).equals(SPACE) && text.substring(i - 1, i).equals(SPACE))
                    continue;

            sb.append(text.substring(i, i + 1));
        }

        text = sb.toString();

        int wordsCounter = 0;

        for (int i = 0; i < text.length(); i++)
            if (text.substring(i, i + 1).equals(SPACE) || i == text.length() - 1)
                wordsCounter++;

        String[] result = new String[wordsCounter];

        int wordLength = 0;
        wordsCounter = 0;
        int spaceIndex = -1;

        for (int i = 0; i < text.length(); i++) {
            boolean isSpace = text.substring(i, i + 1).equals(SPACE);

            if (!isSpace)
                wordLength++;

            if (isSpace || i == text.length() - 1) {
                String word = text.substring(spaceIndex + 1, spaceIndex + wordLength + 1);
                result[wordsCounter] = word;

                wordsCounter++;
                wordLength = 0;
                spaceIndex = i;
            }
        }

        return result;
    }

    private static String toString(String[] words) {
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                sb.append(word);
                sb.append(SPACE);
            }
        }

        return sb.toString();
    }

    private static boolean isPunctuationMark(String symbol) {
        for (String s : PUNCTUATION_MARKS)
            if (s.equals(symbol))
                return true;

        return false;
    }

    private static boolean isConsonant(String letter) {
        for (String consonantLetter : CONSONANTS)
            if (consonantLetter.equalsIgnoreCase(letter))
                return true;

        return false;
    }
}