package ru.job4j.compare;

import java.util.Arrays;

public class CompareString {

    /**
     * Метод проверяет равны ли две строки состоящих из одинаковых символов
     * расположенных в разном порядке.
     * @param str1 first string.
     * @param str2 second string.
     * @return true or false.
     */
    public static boolean stringEquality(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        return sort(str1).equals(sort(str2));
    }

    private static String sort(String str) {
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    /**
     * Метод проверяет что два слова отличаются друг от друга на одну перестановку символов.
     * @param str1 first string.
     * @param str2 second string.
     * @return true or false.
     */
    public static boolean ifOneCharacterTransposition(String str1, String str2) {
        boolean result = false;
        if (str1.length() != str2.length()) {
            return result;
        }
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        int count = 0;
        for (int i = 0; i < ch1.length; i++) {
            if (ch1[i] != ch2[i]) {
                if (ch1[i] == ch2[i + 1] && ch1[i + 1] == ch2[i]) {
                    i++;
                    count++;
                }
            }
        }
        result = count == 1 ? true : false;
        return result;
    }
}
