package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        for (int i = 0; i < table.length; i++) {
            if (table[0][0].hasMarkX() && table[1][1].hasMarkX() && table[2][2].hasMarkX()) {
                return true;
            } else if (table[2][0].hasMarkX() && table[1][1].hasMarkX() && table[0][2].hasMarkX()) {
                return true;
            } else if (table[i][0].hasMarkX() && table[i][1].hasMarkX() && table[i][2].hasMarkX()) {
                return true;
            } else if (table[0][i].hasMarkX() && table[1][i].hasMarkX() && table[2][i].hasMarkX()) {
                return true;
            }
        }
        return false;
    }

    public boolean isWinnerO() {
        for (int i = 0; i < table.length; i++) {
            if (!table[0][0].hasMarkX() && !table[1][1].hasMarkX() && !table[2][2].hasMarkX()) {
                return true;
            } else if (!table[2][0].hasMarkX() && !table[1][1].hasMarkX() && !table[0][2].hasMarkX()) {
                return true;
            } else if (!table[i][0].hasMarkX() && !table[i][1].hasMarkX() && !table[i][2].hasMarkX()) {
                return true;
            } else if (!table[0][i].hasMarkX() && !table[1][i].hasMarkX() && !table[2][i].hasMarkX()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasGap() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (!table[i][j].hasMarkX() && !table[i][j].hasMarkO()) {
                   return true;
                }
            }
        }
        return false;
    }
}