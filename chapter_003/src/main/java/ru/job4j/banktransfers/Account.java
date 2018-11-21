package ru.job4j.banktransfers;

public class Account {
    private double value;
    private String requisites;

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public boolean transfer(Account destination, double amount) {
        boolean succes = false;
        if (amount > 0 && amount < this.value && destination != null) {
            succes = true;
            this.value -= amount;
            destination.value += amount;
        }
        return succes;
    }

    public String getRequisites() {
        return requisites;
    }
}
