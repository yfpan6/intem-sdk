package com.intemanagement.sdk.utils;

/**
 * @Author panyunfeng
 * @Date 2019/12/16
 */
public class Chars {
    public static final char QUOTE = '\'';
    public static final char DOUBLE_QUOTE = '"';
    public static final char PERIOD = '.';
    public static final char DOT = '.';
    public static final char QUESTION = '?';
    public static final char COMMA = ',';
    public static final char SEMICOLON = ';';
    public static final char COLON = ':';
    public static final char SLASH = '/';
    public static final char BACKSLASH = '\\';
    public static final char BAR = '|';
    public static final char TILDE = '~';
    public static final char EXCLAM = '!';
    public static final char AT = '@';
    // hash? pound
    public static final char SHARP = '#';
    public static final char DOLLAR = '$';
    public static final char PERCENT = '%';
    public static final char CARET = '^';
    public static final char AMPERSAND = '&';
    // star
    public static final char ASTERISK = '*';
    public static final char HYPHEN = '-';
    public static final char UNDERSCORE = '_';
    public static final char PLUS = '+';
    public static final char EQUAL = '=';

    public static boolean isLetter(Character c) {
        return c != null
                && ((c >= 'a' && c <= 'z')
                || (c >= 'A' && c <= 'Z'));
    }

    public static boolean isNum(Character c) {
        return c != null && (c >= '0' && c <= '9');
    }

    public static Character toUpperCase(Character c) {
        if (c != null && (c >= 'a' && c <= 'z')) {
            return (char) (c + ('A' - 'a'));
        }

        return c;
    }

    public static Character toLowerCase(Character c) {
        if (c != null && (c >= 'a' && c <= 'z')) {
            return (char) (c + ('A' - 'a'));
        }

        return c;
    }
}
