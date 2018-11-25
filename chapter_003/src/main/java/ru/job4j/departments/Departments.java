package ru.job4j.departments;

import java.util.*;

public class Departments {
    public Set<String> sortDepartments(List<String> divisions) {
        Set<String> result = new TreeSet<>(divisions);
        result.addAll(findDivision(divisions));
        return result;
    }

    public Set<String> reversSortDepartments(List<String> divisions) {
        Set<String> result = new TreeSet<>(Comparator.reverseOrder());
        result.addAll(divisions);
        result.addAll(findDivision(divisions));
        return result;
    }

    private Set<String> findDivision(List<String> divisions) {
        Set<String> result = new HashSet<>();
        for (String string : divisions) {
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == '\\') {
                    String find = string.substring(0, i);
                    if (!divisions.contains(find)) {
                        result.add(find);
                    }
                }
            }
        }
        return result;
    }
}
