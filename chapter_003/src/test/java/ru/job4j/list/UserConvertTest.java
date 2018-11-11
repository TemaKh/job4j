package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {
    @Test
    public void whenMethodTakesListUsersThenConvertListInMapUsers() {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "Ivan", "Stavropol"));
        list.add(new User(2, "Petr", "Moskow"));
        list.add(new User(3, "Artem", "Saint-Petersburg"));
        HashMap<Integer, User> expect = new HashMap<>();
        expect.put(1, new User(1, "Ivan", "Stavropol"));
        expect.put(2, new User(2, "Petr", "Moskow"));
        expect.put(3, new User(3, "Artem", "Saint-Petersburg"));
        UserConvert userConvert = new UserConvert();
        HashMap<Integer, User> result = userConvert.process(list);
        assertThat(result, is(expect));
    }
}
