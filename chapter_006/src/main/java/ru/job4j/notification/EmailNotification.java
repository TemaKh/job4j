package ru.job4j.notification;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Mail distribution service.
 */
public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * Method through ExecutorService sends mail.
     * @param user .
     */
    public void emailTo(User user) {
        String subject = String.format("Notification %s to email %s", user.getUsername(), user.getEmail());
        String body = String.format("Add a new event to %s", user.getUsername());
        pool.submit(() -> send(subject, body, user.getEmail()));
    }

    /**
     * Method close pool.
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {

    }
}
