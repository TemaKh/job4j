package ru.job4j.coffeemachine;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeeMachineTest {
    @Test
    public void whenPrice32Value50Change10and5and2and1() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] result = coffeeMachine.changes(50, 32);
        int[] expected = {10, 5, 2, 1};
        assertThat(result, is(expected));
    }
}
