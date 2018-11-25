package ru.job4j.banktransfers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private Map<User, List<Account>> bankAccounts = new HashMap<>();

    /**
     * Добавляет пользователя в банк.
     * @param user пользователь.
     */
    public void addUser(User user) {
        this.bankAccounts.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Удаляет пользователя из банка.
     * @param user пользователь.
     */
    public void deleteUser(User user) {
        if (user != null) {
            this.bankAccounts.remove(user);
        } else {
            System.out.println("Такого пользователя не существует.");
        }
    }

    /**
     * Добавляет счет пользователю.
     * @param passport паспорт пользователя.
     * @param account счет.
     */
    public void addAccountToUser(String passport, Account account) {
        User user = findUser(passport);
        if (user != null) {
            this.bankAccounts.get(user).add(account);
        } else {
            System.out.println("Такого пользователя не существует.");
        }
    }

    /**
     * Удаляет один счет пользователя.
     * @param passport паспорт пользователя.
     * @param account счет.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        User user = findUser(passport);
        if (user != null) {
            this.bankAccounts.get(findUser(passport)).remove(account);
        } else {
            System.out.println("Такого пользователя не существует.");
        }
    }

    /**
     * Получает список счетов пользователя.
     * @param passport паспорт пользователя.
     * @return список счетов пользователя.
     */
    public List<Account> getUserAccounts(String passport) {
        User user = findUser(passport);
        if (user != null) {
            return this.bankAccounts.get(findUser(passport));
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Ищет пользователя по паспорту.
     * @param passport паспорт пользователя.
     * @return пользователя.
     */
    private User findUser(String passport) {
        for (Map.Entry<User, List<Account>> desired : this.bankAccounts.entrySet()) {
            if (desired.getKey().getPassport().equals(passport)) {
                return desired.getKey();
            }
        }
        return null;
    }

    private Account getActualAccount(String passport, String requisite) {
        Account account = null;
        for (Map.Entry<User, List<Account>> desired : this.bankAccounts.entrySet()) {
            for (Account findAccount : desired.getValue()) {
                if (findAccount.getRequisites().equals(requisite)) {
                    account = findAccount;
                    break;
                }
            }
        }
        List<Account> list = this.bankAccounts.get(findUser(passport));
        return list.get(list.indexOf(account));
    }

    /**
     * Метод для перечисления денег с одного счёта на другой счёт
     * @param srcPassport .
     * @param srcRequisite .
     * @param destPassport .
     * @param destRequisite .
     * @param amount .
     * @return true если операция прошла успешно,
     * если счёт не найден или не хватает денег на счёте srcAccount (с которого переводят) метод должен вернуть false.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String destRequisite, double amount) {
        return getActualAccount(srcPassport, srcRequisite).transfer(getActualAccount(destPassport, destRequisite), amount);
    }

    public Map<User, List<Account>> getBankAccounts() {
        return bankAccounts;
    }
}
