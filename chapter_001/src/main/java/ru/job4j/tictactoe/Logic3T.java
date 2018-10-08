package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        boolean result = true;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                result = true;
                if (!table[i][j].hasMarkX()) {
                    result = false;
                    break;
                }
            }
            if (!result) {
                for (int j = 0; j < table.length; j++) {
                    result = true;
                    if (!table[j][i].hasMarkX()) {
                        result = false;
                        break;
                    }
                }
                if (result) {
                    break;
                }
            }
        }
        if (!result) {
            result = true;
            for (int i = 0; i < table.length; i++) {
                if (!table[i][i].hasMarkX()) {
                    result = false;
                    break;
                }
            }
        }
        if (!result) {
            result = true;
            for (int i = table.length - 1, j = 0; i > 0; i--, j++) {
                if (!table[j][i].hasMarkX()) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    public boolean isWinnerO() {
        boolean result = true;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                result = true;
                if (!table[i][j].hasMarkO()) {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
            if (!result) {
                for (int j = 0; j < table.length; j++) {
                    result = true;
                    if (!table[j][i].hasMarkO()) {
                        result = false;
                        break;
                    }
                }
                if (result) {
                    break;
                }
            }
        }
        if (!result) {
            result = true;
            for (int i = 0; i < table.length; i++) {
                if (!table[i][i].hasMarkO()) {
                    result = false;
                    break;
                }
            }
        }
        if (!result) {
            result = true;
            for (int i = table.length - 1, j = 0; i > 0; i--, j++) {
                if (!table[j][i].hasMarkO()) {
                    result = false;
                    break;
                }
            }
        }
        return result;
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