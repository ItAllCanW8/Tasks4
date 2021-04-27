package by.epamtc.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input {
    public static String readFromConsole() {
        Scanner sc = new Scanner(System.in);

        String text = "";

        while (!sc.hasNextLine())
            sc.next();

        text = sc.nextLine();

        return text;
    }

    public static String readFromFile(String name) throws FileNotFoundException {
        if (name == null)
            //NullPtrException
            return "";

        if (name.isEmpty())
            //WrongFileException
            return "";

        File file = new File(name);
        Scanner sc = new Scanner(file);
        StringBuilder stringBuilder = new StringBuilder();

        while (sc.hasNext()) {
            stringBuilder.append(sc.nextLine());
        }

        return stringBuilder.toString();
    }
}
