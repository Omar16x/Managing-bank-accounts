package com.bank.accounts;

import com.bank.exceptions.BankOperationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class SavingsAccountTest {

    private SavingsAccount savingAccount;

    @BeforeEach
    void setUp() {
        savingAccount = new SavingsAccount(1L, "Omar", 1000);
    }
    @Test
    void depositValidAmount() {
        savingAccount.deposit(300);
        assertEquals(1300, savingAccount.getBalance());
    }

    @Test
    void depositZeroAmount() {
        assertThrows(BankOperationException.class, () -> savingAccount.deposit(0));
        assertEquals(1000, savingAccount.getBalance());
    }

    @Test
    void depositNegativeAmount() {
        assertThrows(BankOperationException.class, () -> savingAccount.withdraw(-20));
        assertEquals(1000, savingAccount.getBalance());
    }

    @Test
    void withdrawValidAmount() {
        savingAccount.withdraw(200);
        assertEquals(800, savingAccount.getBalance());
    }

    @Test
    void withdrawExceedingAmount() {
        assertThrows(BankOperationException.class, () -> savingAccount.withdraw(1500));
        assertEquals(1000, savingAccount.getBalance());
    }

    @Test
    void withdrawAllThenDeposit() {
        savingAccount.withdraw(1000);
        savingAccount.deposit(300);
        assertEquals(300, savingAccount.getBalance());
    }

    @Test
    void multipleDepositsAndWithdrawals() {
        savingAccount.deposit(1200);
        savingAccount.withdraw(1000);
        savingAccount.deposit(300);
        savingAccount.withdraw(400);
        assertEquals(1100, savingAccount.getBalance());
    }

    @Test
    void calculateInterest() {
        savingAccount.calculateInterest();
        assertEquals(1030, savingAccount.getBalance());
    }
}

