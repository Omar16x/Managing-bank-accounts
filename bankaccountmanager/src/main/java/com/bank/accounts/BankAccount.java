package com.bank.accounts;

/**
 * Abstract class representing a bank account.
 */
public abstract class BankAccount {
    protected final Long accountNumber;
    protected final String accountHolder;
    protected double balance;

    /**
     * Constructor for BankAccount.
     *
     * @param accountNumber the account number
     * @param accountHolder the name of the account holder
     * @param balance       the initial balance of the account
     */
    protected BankAccount(Long accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    /** Method to deposit an amount of money into the account.
     * @param amount The amount to withdraw.
     */
    public abstract void deposit(double amount);
    /** Method to withdraw an amount of money from the account.
     * @param amount The amount to withdraw.
     */
    public abstract void withdraw(double amount);
    /** Method to calculate interest.
     */
    public abstract void calculateInterest();

    /**
     * Method to get the current balance.
     *
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Method to get the account number.
     *
     * @return the account number
     */
    public Long getAccountNumber() {
        return accountNumber;
    }

    /**
     * Method to get the account holder.
     *
     * @return the account holder
     */
    public String getAccountHolder() {
        return accountHolder;
    }

    @Override
    public String toString(){
        return "Account number : " + this.accountNumber + " | Holder's name : " + this.accountHolder + " | Balance : " + this.balance + "\n";
    }
}
