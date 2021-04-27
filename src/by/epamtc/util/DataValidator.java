package by.epamtc.util;

public class DataValidator {
    public static void checkForNull(String text){
        if (text == null) {
            //NullPtrException
        }
    }
    public static void checkBounds(int bound){
        if (bound < 0) {
            //OutOfBoundsException
        }
    }
}
