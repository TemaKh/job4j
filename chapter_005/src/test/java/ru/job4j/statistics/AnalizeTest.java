package ru.job4j.statistics;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalizeTest {
    private Analize analize;
    private List<Analize.User> previous;
    private List<Analize.User> current;
    private Analize.Info info;

    @Before
    public void setUp() {
        analize = new Analize();
        previous = new ArrayList<>();
        current = new ArrayList<>();
        previous.add(new Analize.User(1, "Anna"));
        previous.add(new Analize.User(2, "Artur"));
        previous.add(new Analize.User(3, "Tom"));
        previous.add(new Analize.User(4, "Ivan"));

        current.add(new Analize.User(1, "Anna"));
        current.add(new Analize.User(6, "Victor"));
        current.add(new Analize.User(2, "Tom"));
        current.add(new Analize.User(4, "Ivan"));
        current.add(new Analize.User(5, "Artem"));

        info = analize.diff(previous, current);
    }

    @Test
    public void whenUserAdded() {
        assertThat(info.getAdded(), is(2));
    }

    @Test
    public void whenUserChanged() {
        assertThat(info.getChanged(), is(1));
    }

    @Test
    public void whenUserDeleted() {
        assertThat(info.getDeleted(), is(1));
    }
}