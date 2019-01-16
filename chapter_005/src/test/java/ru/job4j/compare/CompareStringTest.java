package ru.job4j.compare;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CompareStringTest {
    private CompareString compareString;

    @Before
    public void setUp() {
        compareString = new CompareString();
    }

    @Test
    public void whenTwoStringEqualThenReturnTrue() {
        String str1 = "мама";
        String str2 = "амам";
        assertThat(compareString.stringEquality(str1, str2), is(true));
    }

    @Test
    public void whenTwoStringNotEqualThenReturnFalse() {
        String str1 = "мама";
        String str2 = "апам";
        assertThat(compareString.stringEquality(str1, str2), is(false));
    }

    @Test
    public void whenOneCharacterTranspositionThenReturnTrue() {
        String str1 = "слово";
        String str2 = "слоов";
        assertThat(compareString.ifOneCharacterTransposition(str1, str2), is(true));
    }

    @Test
    public void whenTwoOrMoreCharacterTranspositionThenReturnFalse() {
        String str1 = "слово";
        String str2 = "лсоов";
        assertThat(compareString.ifOneCharacterTransposition(str1, str2), is(false));
    }
}