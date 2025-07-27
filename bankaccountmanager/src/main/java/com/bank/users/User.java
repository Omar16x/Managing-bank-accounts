package com.bank.users;

import com.bank.accounts.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final long idUser;
    private final List<BankAccount> bankAccounts;

    public User(long idUser) {
        this.idUser = idUser;
        this.bankAccounts = new ArrayList<>();
    }

    public long getIdUser() {
        return idUser;
    }

    public List<BankAccount> getAccounts() {
        return bankAccounts;
    }

    public void addAccount(BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
    }
}


