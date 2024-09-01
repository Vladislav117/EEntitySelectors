package ru.vladislav117.eentityselectors.utils;

public final class StringUtils {
    public static String removePrefix(String string, String prefix) {
        if (!string.startsWith(prefix)) return string;
        if (string.length() == prefix.length()) return "";
        return string.substring(prefix.length());
    }
}
