package ru.job4j.list;

import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() / rows;
        if (list.size() % rows != 0) {
            cells++;
        }
        int index = 0;
        int[][] array = new int[rows][cells];
        for (int out = 0; out < array.length; out++) {
            for (int in = 0; in < array[out].length; in++) {
                if (index < list.size()) {
                    array[out][in] = list.get(index++);
                }
            }
        }
        return array;
    }
}
