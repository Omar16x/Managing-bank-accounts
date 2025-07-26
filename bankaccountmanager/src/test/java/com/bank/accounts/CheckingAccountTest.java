package com.bank.accounts;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckingAccountTest {

    private CheckingAccount checkingAccount;

    @BeforeEach
    void setUp() {
        checkingAccount = new CheckingAccount("1", "Omar", 1000);
    }

    @Test
    void depositValidAmount() {
        checkingAccount.deposit(50);
        assertEquals(1050, checkingAccount.getBalance());
    }

    @Test
    void depositNegativeAmount() {
        checkingAccount.deposit(-20);
        assertEquals(1000, checkingAccount.getBalance());
    }

    @Test
    void withdrawValidAmount() {
        checkingAccount.withdraw(50);
        assertEquals(950, checkingAccount.getBalance());
    }

    @Test
    void withdrawExceedingAmount() {
        checkingAccount.withdraw(1200);
        assertEquals(1000, checkingAccount.getBalance());
    }

    @Test
    void withdrawNegativeAmount() {
        checkingAccount.withdraw(-30);
        assertEquals(1000, checkingAccount.getBalance());
    }

    @Test
    void withdrawAllThenDeposit() {
        checkingAccount.withdraw(1000);
        checkingAccount.deposit(300);
        assertEquals(300, checkingAccount.getBalance());
    }

    @Test
    void multipleDepositsAndWithdrawals() {
        checkingAccount.deposit(1200);
        checkingAccount.withdraw(1000);
        checkingAccount.deposit(300);
        checkingAccount.withdraw(400);
        assertEquals(1100, checkingAccount.getBalance());
    }

    @Test
    void calculateInterests() {
        double oldBalance = checkingAccount.getBalance();
        checkingAccount.calculateInterest();
        assertEquals(oldBalance, checkingAccount.getBalance());
    }
}
