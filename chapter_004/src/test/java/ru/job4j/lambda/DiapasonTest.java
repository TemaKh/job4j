package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DiapasonTest {
    @Test
    public void whenLineFunction() {
        Diapason diapason = new Diapason();
        List<Double> result = diapason.diapason(1, 3, n -> n);
        assertThat(result, is(Arrays.asList(1D, 2D, 3D)));
    }

    @Test
    public void whenQuadraticFunction() {
        Diapason diapason = new Diapason();
        List<Double> result = diapason.diapason(1, 3, n -> n * n);
        assertThat(result, is(Arrays.asList(1D, 4D, 9D)));
    }

    @Test
    public void whenLogarithmicFunction() {
        Diapason diapason = new Diapason();
        List<Double> result = diapason.diapason(1, 3, Math::exp);
        assertThat(result, is(Arrays.asList(2.718281828459045, 7.38905609893065, 20.085536923187668)));
    }
}
