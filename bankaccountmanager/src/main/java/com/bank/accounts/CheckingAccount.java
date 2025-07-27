package com.bank.accounts;


import com.bank.exceptions.BankOperationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class representing a checking account
 * No interests calculated.
 */
public final class CheckingAccount extends BankAccount {
    private static final Logger logger = LogManager.getLogger(CheckingAccount.class);

    /**
     * Constructor for CheckingAccount.
     *
     * @param accountNumber the account number
     * @param accountHolder the name of the account holder
     * @param balance       the initial balance of the account
     */
    public CheckingAccount(Long accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new BankOperationException("The amount should be positive number.");
        }
        balance += amount;
        logger.info("Deposit of {} into the checking account number {}", amount, accountNumber);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            throw new BankOperationException("Invalid amount of money.");
        }
        balance -= amount;
        logger.info("Withdrawal of {} from the checking account number {}", amount, accountNumber);
    }

    @Override
    public void calculateInterest() {
        logger.info("No interest for a checking account.");
    }
}
