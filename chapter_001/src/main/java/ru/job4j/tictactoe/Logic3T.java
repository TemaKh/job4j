package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        for (int i = 0; i < table.length; i++) {
            if (table[0][0].hasMarkX() == true && table[1][1].hasMarkX() == true && table[2][2].hasMarkX() == true) {
                return true;
            } else if (table[2][0].hasMarkX() == true && table[1][1].hasMarkX() == true && table[0][2].hasMarkX() == true) {
                return true;
            } else if (table[i][0].hasMarkX() == true && table[i][1].hasMarkX() == true && table[i][2].hasMarkX() == true) {
                return true;
            } else if (table[0][i].hasMarkX() == true && table[1][i].hasMarkX() == true && table[2][i].hasMarkX() == true) {
                return true;
            }
        }
        return false;
    }

    public boolean isWinnerO() {
        for (int i = 0; i < table.length; i++) {
            if (table[0][0].hasMarkX() == false && table[1][1].hasMarkX() == false && table[2][2].hasMarkX() == false) {
                return true;
            } else if (table[2][0].hasMarkX() == false && table[1][1].hasMarkX() == false && table[0][2].hasMarkX() == false) {
                return true;
            } else if (table[i][0].hasMarkX() == false && table[i][1].hasMarkX() == false && table[i][2].hasMarkX() == false) {
                return true;
            } else if (table[0][i].hasMarkX() == false && table[1][i].hasMarkX() == false && table[2][i].hasMarkX() == false) {
                return true;
            }
        }
        return false;
    }

    public boolean hasGap() {
        int counter = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j].hasMarkX() == false && table[i][j].hasMarkO() == false) {
                    counter++;
                }
            }
        }
        return counter > 0 ? true : false;
    }
}