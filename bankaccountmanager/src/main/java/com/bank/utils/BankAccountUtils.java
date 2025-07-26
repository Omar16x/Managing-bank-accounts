package com.bank.utils;

import com.bank.accounts.BankAccount;

public class BankAccountUtils {
    public static void displayCalculationDetails(BankAccount account, double interestAmount) {
        System.out.printf(
                "Account [%s] (%s): Interest calculated = %.2f | New balance = %.2f%n",
                account.getAccountNumber(),
                account.getAccountHolder(),
                interestAmount,
                account.getBalance()
        );
    }
}
