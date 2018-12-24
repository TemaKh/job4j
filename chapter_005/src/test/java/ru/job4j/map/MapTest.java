package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class MapTest {
    @Test
    public void whenEqualsAndHashCodeNorOverriding() {
        Map<User, Object> map = new HashMap<>();
        map.put(new User("Tom", 0, new GregorianCalendar(1995, 4, 20)), new Object());
        map.put(new User("Tom", 0, new GregorianCalendar(1995, 4, 20)), new Object());
        System.out.println(map);
    }
}
