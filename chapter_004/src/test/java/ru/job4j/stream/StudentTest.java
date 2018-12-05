package ru.job4j.stream;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StudentTest {
    @Test
    public void whenStudentScopeMoreBound() {
        List<Student> students = List.of(
                new Student("Tom", 7),
                new Student("Max", 3),
                new Student("Sara", 9),
                new Student("Anton", 2)
        );
        List<Student> expect = List.of(
                new Student("Tom", 7),
                new Student("Sara", 9)
                );
        assertThat(Student.levelOf(students, 5), is(expect));
    }
}
