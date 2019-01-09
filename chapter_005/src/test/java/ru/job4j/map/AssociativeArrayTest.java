package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AssociativeArrayTest {
    private AssociativeArray<User, Integer> associativeArray;
    private Iterator<Integer> iterator;
    private User user1;
    private User user2;
    private User user3;
    private User user4;
    private User user5;
    private User user6;

    @Before
    public void setUp() {
        associativeArray = new AssociativeArray<>();
        user1 = new User("Tom", 0, new GregorianCalendar(1995, 4, 20));
        user2 = new User("Kate", 1, new GregorianCalendar(1990, 7, 23));
        user3 = new User("Anton", 3, new GregorianCalendar(1987, 1, 15));
        user4 = new User("Roma", 0, new GregorianCalendar(1997, 8, 6));
        user5 = new User("Petr", 1, new GregorianCalendar(1987, 5, 17));
        user6 = new User("Artur", 2, new GregorianCalendar(1980, 10, 27));
        associativeArray.insert(user1, 1);
        associativeArray.insert(user2, 2);
        iterator = associativeArray.iterator();
    }

    @Test
    public void whenGetByKey() {
        assertThat(associativeArray.get(user2), is(2));
    }

    @Test
    public void whenAddItemsLargerThanDefaultCapacity() {
        associativeArray.insert(user3, 3);
        associativeArray.insert(user4, 4);
        associativeArray.insert(user5, 5);
        associativeArray.insert(user6, 6);
        assertThat(associativeArray.get(user6), is(6));
    }

    @Test
    public void whenKeysMatch() {
        associativeArray.insert(user1, 3);
        assertThat(associativeArray.get(user1), is(3));
    }

    @Test
    public void whenIteratorReturnValue() {
        assertThat(iterator.hasNext(), is(true));
        iterator.next();
        assertThat(iterator.hasNext(), is(true));
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void whenDeleteElement() {
        assertThat(associativeArray.delete(user2), is(true));
    }
}