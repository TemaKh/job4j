package ru.job4j.sorting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    @Test
    public void whenMethodTakesListUsersThenReturnTreeSetSortedByAge() {
        List<User> list = new ArrayList<>();
        list.add(new User("Mike", 34));
        list.add(new User("Tom", 23));
        list.add(new User("Salomon", 30));
        Set<User> expect = new TreeSet<>();
        expect.add(new User("Mike", 30));
        expect.add(new User("Tom", 34));
        expect.add(new User("Salomon", 23));
        SortUser sortUser = new SortUser();
        Set<User> result = sortUser.sort(list);
        assertThat(result, is(expect));
    }
}
