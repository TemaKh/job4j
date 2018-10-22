package ru.job4j.tracker;

public class ValidateInput extends ConsoleInput {
    @Override
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Пожалуйста введите значения из диапазона меню");
            } catch (NumberFormatException nfe) {
                System.out.println("Пожалуйста ввидите корректное значение");
            }
        } while (invalid);
        return value;
    }
}
