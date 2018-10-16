package ru.job4j.pseudo;

public class Triangle implements Shape {
    @Override
    public String draw() {
        StringBuilder triangle = new StringBuilder();
        String ls = System.lineSeparator();
        triangle.append("  +  ").append(ls);
        triangle.append(" +++ ").append(ls);
        triangle.append("+++++").append(ls);
        return triangle.toString();
    }
}
