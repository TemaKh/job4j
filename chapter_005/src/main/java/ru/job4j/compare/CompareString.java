package ru.job4j.compare;

import java.util.Arrays;
import java.util.HashMap;

public class CompareString {

    /**
     * Метод проверяет равны ли две строки состоящих из одинаковых символов
     * расположенных в разном порядке.
     * @param str1 first string.
     * @param str2 second string.
     * @return true or false.
     */
    public boolean stringEquality(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int count = 1;
        for (Character ch1 : str1.toCharArray()) {
            if (!map.containsKey(ch1)) {
                map.put(ch1, count);
            } else {
                map.put(ch1, count + 1);
            }
        }

        for (Character ch2 : str2.toCharArray()) {
            if (!map.containsKey(ch2)) {
                return false;
            }
            if (map.containsKey(ch2)) {
                map.put(ch2, map.get(ch2) - 1);
            }
            if (map.get(ch2).equals(0)) {
                map.remove(ch2);
            }
        }

        return map.isEmpty();
    }

    /**
     * Метод проверяет что два слова отличаются друг от друга на одну перестановку символов.
     * @param str1 first string.
     * @param str2 second string.
     * @return true or false.
     */
    public boolean ifOneCharacterTransposition(String str1, String str2) {
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
