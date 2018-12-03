package ru.job4j.sorting;

import java.util.*;

public class SortUser {
    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }

    public List<User> sortNameLength(List<User> list) {
        Collections.sort(list, ((o1, o2) -> (Integer.compare(o1.getName().length(), o2.getName().length()))));
        return list;
    }

    public List<User> sortByAllFields(List<User> list) {
        Collections.sort(list, (o1, o2) -> {
                int i = o1.getName().compareTo(o2.getName());
                if (i == 0) {
                    return o1.compareTo(o2);
                } else {
                    return i;
                }
        });
        return list;
    }
}
