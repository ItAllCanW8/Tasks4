package by.epamtc.service;

import by.epamtc.util.DataValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExSolver {
    public static final String SPACE = " ";
    public static final String PUNCTUATION_REG_EX = "(\\w+)([,.!?:-;]?)(\\s?)";
    public static final char   WRONG_LETTER = 'A';
    public static final char   WRONG_LETTER_PREFIX = 'P';
    public static final char   DESIRED_LETTER = 'O';
    public static final String CONSONANTS = "^[bcdfghjklmnpqrstvwxyz]";
    public static final String ALPHABET_REG_EX = "([a-zA-Z]+)([,.!?:-;]?)(\\s?)";

    public static String replaceLetters(String text, int k, char symbol){
        DataValidator.checkForNull(text);
        DataValidator.checkBounds(k);

        Matcher matcher = createMatcher(text, PUNCTUATION_REG_EX, false);

        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            StringBuilder word = new StringBuilder(matcher.group(1));

            if (k - 1 < word.length())
                word.setCharAt(k - 1, symbol);

            word.append(matcher.group(2));
            word.append(matcher.group(3));

            sb.append(word);
        }

        return sb.toString();
    }

    public static String replaceWrongLetter(String text){
        DataValidator.checkForNull(text);

        Matcher matcher = createMatcher(text, PUNCTUATION_REG_EX, false);

        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            StringBuilder word = new StringBuilder(matcher.group(1));

            if (word.charAt(word.length() - 1) == WRONG_LETTER && word.charAt(word.length() - 2) == WRONG_LETTER_PREFIX)
                word.setCharAt(word.length() - 1, DESIRED_LETTER);

            word.append(matcher.group(2));
            word.append(matcher.group(3));
            sb.append(word);
        }

        return sb.toString();
    }

    public static String replaceWordsWithSubstring(String text, String substring, int length){
        DataValidator.checkForNull(text);
        DataValidator.checkBounds(length);

        Matcher matcher = createMatcher(text, PUNCTUATION_REG_EX, false);

        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            if (matcher.group(1).length() == length) {
                sb.append(substring);
                sb.append(matcher.group(2));
                sb.append(matcher.group(3));
            } else
                sb.append(matcher.group());
        }

        return sb.toString();
    }

    public static String removeRedundantCharacters(String text){
        DataValidator.checkForNull(text);

        Matcher matcher = createMatcher(text, PUNCTUATION_REG_EX, false);

        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            sb.append(matcher.group(1));
            sb.append(SPACE);
        }

        return sb.toString();
    }

    public static String removeConsWords(String text, int length){
        DataValidator.checkForNull(text);
        DataValidator.checkBounds(length);
        Matcher matcher = createMatcher(text, ALPHABET_REG_EX, false);

        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            Matcher wordMatcher = createMatcher(matcher.group(1), CONSONANTS, true);

            if (matcher.group(1).length() != length || !wordMatcher.find())
                sb.append(matcher.group(1));

            sb.append(matcher.group(2));
            sb.append(matcher.group(3));
        }

        return sb.toString();
    }

    private static Matcher createMatcher(String text, String regEx, boolean isCaseInsensitive) {
        Pattern pattern = isCaseInsensitive ? Pattern.compile(regEx, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)
                : Pattern.compile(regEx);

        return pattern.matcher(text);
    }
}
