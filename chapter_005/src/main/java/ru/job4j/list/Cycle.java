package ru.job4j.list;

public class Cycle {

    public static boolean hasCycle(Node first) {
        Node one = first;
        Node two = first.next;
        while (one != null && two != null) {
            one = one.next;
            two = two.next;
            if (two == null) {
                return false;
            }
            if (one.equals(two.next) || two.equals(first)) {
                return true;
            }
        }
        return false;
    }
}
