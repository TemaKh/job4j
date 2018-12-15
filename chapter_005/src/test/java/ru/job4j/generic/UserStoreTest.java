package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserStoreTest {
    private UserStore userStore;

    @Before
    public void setUp() {
        userStore = new UserStore(4);
        userStore.add(new User("1"));
        userStore.add(new User("2"));
        userStore.add(new User("3"));
    }

    @Test
    public void whenAddElement() {
        userStore = new UserStore(1);
        userStore.add(new User("1"));
        assertThat(userStore.findById("1"), is(new User("1")));
    }

    @Test
    public void whenReplaceElement() {
        assertThat(userStore.replace("1", new User("5")), is(true));
    }

    @Test
    public void whenDeleteElement() {
        assertThat(userStore.delete("1"), is(true));
    }

    @Test
    public void whenFindByIdElement() {
        assertThat(userStore.findById("1"), is(new User("1")));
    }
}