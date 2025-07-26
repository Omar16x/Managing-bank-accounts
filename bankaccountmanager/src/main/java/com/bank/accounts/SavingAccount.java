package com.bank.accounts;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class representing a saving account with 3% interest.
 */
public final class SavingAccount extends BankAccount {
    private static final Logger logger = LogManager.getLogger(SavingAccount.class);
    private static final double INTEREST_RATE = 0.03;

    /**
     * Constructor for SavingAccount.
     *
     * @param accountNumber the account number
     * @param accountHolder the name of the account holder
     * @param balance       the initial balance of the account
     */
    public SavingAccount(String accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            logger.warn("the amount should be positive.");
            return;
        }
        balance += amount;
        logger.info("Deposit of {} into the account number {}", amount, accountNumber);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            logger.warn("Invalid amount.");
            return;
        }
        balance -= amount;
        logger.info("Withdraw of {} from the account number {}", amount, accountNumber);
    }

    @Override
    public void calculateInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        logger.info("Interest of {} added to the account number {}", interest, accountNumber);
    }
}



