package ru.job4j.pseudo;

public class Square implements Shape {
    @Override
    public String draw() {
        StringBuilder square = new StringBuilder();
        String ls = System.lineSeparator();
        square.append("++++").append(ls);
        square.append("++++").append(ls);
        square.append("++++").append(ls);
        square.append("++++").append(ls);
        return square.toString();
    }
}
