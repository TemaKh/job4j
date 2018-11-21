package ru.job4j.banktransfers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankTest {
    private User tom = new User("Tom", "Cat Tom");
    private User jerry = new User("Jerry", "Mouse Jerry");
    private Account account1Tom = new Account(1000, "08642");
    private Account account2Tom = new Account(1000, "09753");
    private Account accountJerry = new Account(2000, "02468");

    @Test
    public void whenAddUser() {
        Bank bank = new Bank();
        bank.addUser(tom);
        assertThat(bank.getBankAccounts().containsKey(tom), is(true));
    }

    @Test
    public void whenDeleteUser() {
        Bank bank = new Bank();
        bank.deleteUser(tom);
        assertThat(bank.getBankAccounts().containsKey(tom), is(false));
    }

    @Test
    public void whenAddAccountToUser() {
        Bank bank = new Bank();
        bank.addUser(tom);
        bank.addAccountToUser("Cat Tom", account1Tom);
        bank.addAccountToUser("Cat Tom", account2Tom);
        List<Account> accounts = new ArrayList<>();
        accounts.add(account1Tom);
        accounts.add(account2Tom);
        assertThat(bank.getUserAccounts("Cat Tom"), is(accounts));
    }

    @Test
    public void whenDeleteAccountFromUser() {
        Bank bank = new Bank();
        bank.addUser(tom);
        bank.addAccountToUser("Cat Tom", account1Tom);
        bank.addAccountToUser("Cat Tom", account2Tom);
        bank.deleteAccountFromUser("Cat Tom", account2Tom);
        assertThat(bank.getBankAccounts().get(tom).contains(account2Tom), is(false));
    }

    @Test
    public void whenGetUserAccounts() {
        Bank bank = new Bank();
        bank.addUser(jerry);
        bank.addAccountToUser("Mouse Jerry", accountJerry);
        List<Account> accounts = new ArrayList<>();
        accounts.add(accountJerry);
        assertThat(bank.getUserAccounts("Mouse Jerry"), is(accounts));
    }

    @Test
    public void whenTransferMoneyWithAccountTomOnOtherAccountTom() {
        Bank bank = new Bank();
        bank.addUser(tom);
        bank.addAccountToUser("Cat Tom", account1Tom);
        bank.addAccountToUser("Cat Tom", account2Tom);
        assertThat(bank.transferMoney("Cat Tom", "08642", "Cat Tom", "09753", 500), is(true));
    }

    @Test
    public void whenTransferMoneyWithAccountTomOnAccountJerry() {
        Bank bank = new Bank();
        bank.addUser(tom);
        bank.addUser(jerry);
        bank.addAccountToUser("Cat Tom", account1Tom);
        bank.addAccountToUser("Mouse Jerry", accountJerry);
        assertThat(bank.transferMoney("Cat Tom", "08642", "Mouse Jerry", "02468", 500), is(true));
    }
}
