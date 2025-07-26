package com.bank.account;

import com.bank.accounts.BankAccount;
import com.bank.accounts.CheckingAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CheckingAccountTest {
    @Test
    void depositValidAmount() {
        BankAccount account = new CheckingAccount("1", "Omar", 50);
        account.deposit(50);
        assertEquals(100, account.getBalance());
    }

    @Test
    void depositNegativeAmount() {
        BankAccount account = new CheckingAccount("1", "Omar", 100);
        account.deposit(-20);
        assertEquals(100, account.getBalance());
    }

    @Test
    void withdrawValidAmount() {
        BankAccount account = new CheckingAccount("2", "holder", 150);
        account.withdraw(50);
        assertEquals(100, account.getBalance());
    }

    @Test
    void withdrawExceedingAmount() {
        BankAccount account = new CheckingAccount("2", "holder", 100);
        account.withdraw(200);
        assertEquals(100, account.getBalance());
    }

    @Test
    void withdrawNegativeAmount() {
        BankAccount account = new CheckingAccount("3", "holder", 100);
        account.withdraw(-30);
        assertEquals(100, account.getBalance());
    }
}
