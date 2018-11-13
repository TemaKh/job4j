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

    @Test
    public void whenMethodTakesListUsersThenReturnListSortedTheLengthOfTheName() {
        List<User> list = new ArrayList<>();
        User mike = new User("Mike", 34);
        User tom = new User("Tom", 23);
        User salomon = new User("Salomon", 30);
        list.add(mike);
        list.add(tom);
        list.add(salomon);
        List<User> expect = new ArrayList<>();
        expect.add(tom);
        expect.add(mike);
        expect.add(salomon);
        SortUser sortUser = new SortUser();
        List<User> result = sortUser.sortNameLength(list);
        assertThat(result, is(expect));
    }

    @Test
    public void whenMethodTakesListUsersThenReturnListSortedByAllFields() {
        List<User> list = new ArrayList<>();
        User mike25 = new User("Mike", 25);
        User tom30 = new User("Tom", 30);
        User mike20 = new User("Mike", 20);
        User tom25 = new User("Tom", 25);
        list.add(mike25);
        list.add(tom30);
        list.add(mike20);
        list.add(tom25);
        List<User> expect = new ArrayList<>();
        expect.add(mike20);
        expect.add(mike25);
        expect.add(tom25);
        expect.add(tom30);
        SortUser sortUser = new SortUser();
        List<User> result = sortUser.sortByAllFields(list);
        assertThat(result, is(expect));
    }
}
