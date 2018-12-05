package ru.job4j.stream;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Student implements Comparable<Student> {
    private String name;
    private int scope;

    public Student(String name, int scope) {
        this.name = name;
        this.scope = scope;
    }

    public int getScope() {
        return scope;
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(scope, o.scope);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return scope == student.scope && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, scope);
    }

    public static List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .sorted()
                .flatMap(Stream::ofNullable)
                .dropWhile(student -> student.getScope() < bound)
                .collect(Collectors.toList());
    }
}
