package ru.job4j.departments;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DepartmentsTest {
    @Test
    public void whenSortDepartments() {
        Departments departments = new Departments();
        Set<String> result = departments.sortDepartments(Arrays.asList("K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"));
        List<String> list = Arrays.asList("K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK2",
                "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2");
        Set<String> expect = new TreeSet<>(list);
        assertThat(result, is(expect));
    }

    @Test
    public void whenReversSortDepartments() {
        Departments departments = new Departments();
        Set<String> result = departments.reversSortDepartments(Arrays.asList("K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"));
        List<String> list = Arrays.asList("K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1",
                "K1", "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1");
        Set<String> expect = new TreeSet<>(list);
        assertThat(result, is(expect));
    }
}
