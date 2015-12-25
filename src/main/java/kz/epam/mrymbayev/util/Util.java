package kz.epam.mrymbayev.util;

public class Util {
    public static int localeConverter(String locale) {
        int intLocale = 0;
        switch (locale) {
            case "kk":
                intLocale = 1;
                break;
            case "ru":
                intLocale = 2;
                break;
            case "en":
                intLocale = 3;
                break;
        }
        return intLocale;
    }
}
