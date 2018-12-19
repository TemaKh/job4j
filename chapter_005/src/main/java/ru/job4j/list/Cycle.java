package ru.job4j.list;

public class Cycle {

    public static boolean hasCycle(Node first) {
        Node one = first;
        Node two = first;
        while (one != null && two != null) {
            one = one.next;
            two = two.next.next;
            if (one.equals(two)) {
                return true;
            }
        }
        return false;
    }
}
