package ru.job4j.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analize {

    /**
     * Метод возвращает статистику об изменении коллецции.
     * @param previous начальные данные.
     * @param current измененные данные.
     * @return возвращает информацию
     * сколько добавлено новых пользьзователей,
     * сколько изменено пользователей,
     * сколько удалено пользователей.
     */
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, String> mapCurrent = new HashMap<>();
        for (User user : current) {
            mapCurrent.put(user.id, user.name);
        }
        for (User user : previous) {
            if (!mapCurrent.containsKey(user.id)) {
                info.deleted++;
            }
            if (!mapCurrent.containsValue(user.name)) {
                info.changed++;
            }
        }
        info.added = Math.abs((previous.size() - info.deleted) - current.size());
        return info;
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            User user = (User) o;

            return id == user.id;
        }

        @Override
        public int hashCode() {
            return id;
        }
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }
    }
}
