package ru.job4j.synchron;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserStorageTest {
    private class ThreadAddUser extends Thread {
        private final UserStorage userStorage;

        private ThreadAddUser(final UserStorage userStorage) {
            this.userStorage = userStorage;
        }

        @Override
        public void run() {
            userStorage.add(new User(1, 100));
        }
    }

    @Test
    public void whenAddAndTransfer() throws InterruptedException {
        final UserStorage storage = new UserStorage();
        storage.add(new User(2, 200));
        Thread thread = new ThreadAddUser(storage);
        thread.start();
        thread.join();
        storage.transfer(1, 2, 50);
        assertThat(storage.getUser(2).getAmount(), is(250));
    }
}