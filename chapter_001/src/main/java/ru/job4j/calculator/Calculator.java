package ru.job4j.calculator;

/**
 * Class Calculator.
 * @author Artem Khizhniakov (mailto:artem_khizhniakov@mail.ru).
 * @version $Id$.
 * @since 17.09.2018.
 */
public class Calculator {
    /**
     * field result.
     */
    private double result;

    /**
     * Method add.
     * @param first .
     * @param second .
     * Method records sum two param (first plus second) in field result.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Method subtract.
     * @param first .
     * @param second .
     * Method records subtract two param (first minus second) in field result.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Method div.
     * @param first .
     * @param second .
     * Method records division two param (first divide second) in field result.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Method multiple.
     * @param first .
     * @param second .
     * Method records multiple two param (first multiply second) in field result.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * Method getResult.
     * @return field result.
     */
    public double getResult() {
        return this.result;
    }
}
