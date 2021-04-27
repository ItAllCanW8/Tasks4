package by.epamtc.main;

import by.epamtc.service.CharSolver;
import by.epamtc.service.RegExSolver;
import by.epamtc.service.StringSolver;
import by.epamtc.util.Input;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String text = Input.readFromFile("text.txt");

        System.out.println(StringSolver.replaceLetters(text,5, "$"));
        System.out.println(CharSolver.replaceLetters(text, 5, '$'));
        System.out.println(RegExSolver.replaceLetters(text, 5, '$'));

        text = "LorPA ipsum dolo sit amPA, conPctetur adipiscinP ePA.";

        System.out.println("========================================================");
        System.out.println(StringSolver.replaceWrongLetter(text));
        System.out.println(CharSolver.replaceWrongLetter(text));
        System.out.println(RegExSolver.replaceWrongLetter(text));

        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

        System.out.println("========================================================");
        System.out.println(StringSolver.replaceSubstring(text, "qwerty", 4));
        System.out.println(CharSolver.replaceSubstring(text, "qwerty", 4));
        System.out.println(RegExSolver.replaceWordsWithSubstring(text, "qwerty", 4));

        text = "Lorem! ipsum: dolor sit. amet, consectetur; adipiscing? elit.";

        System.out.println("========================================================");
        System.out.println(StringSolver.removeRedundantCharacters(text));
        System.out.println(CharSolver.removeRedundantCharacters(text));
        System.out.println(RegExSolver.removeRedundantCharacters(text));

        text = "Lorem ipsum dolor sit amet, consectetur dolor adipiscing elit dolor.";

        System.out.println("========================================================");
        System.out.println(StringSolver.removeConsWords(text, 5));
        System.out.println(CharSolver.removeConsWords(text, 5));
        System.out.println(RegExSolver.removeConsWords(text, 5));
    }
}
