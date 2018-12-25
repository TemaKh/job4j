package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MapTest {
    @Test
    public void whenEqualsAndHashCodeNorOverriding() {
        Map<User, Object> map = new HashMap<>();
        User user1 = new User("Tom", 0, new GregorianCalendar(1995, 4, 20));
        User user2 = new User("Tom", 0, new GregorianCalendar(1995, 4, 20));
        map.put(user1, new Object());
        map.put(user2, new Object());
        assertThat(user1.equals(user2), is(true));
        System.out.println(user1.equals(user2));
        System.out.println(map);
    }
}
