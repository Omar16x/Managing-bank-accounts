package com.bank.application;


import java.util.*;

import com.bank.accounts.BankAccount;
import com.bank.accounts.CheckingAccount;
import com.bank.accounts.SavingsAccount;
import com.bank.utils.BankAccountUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.bank.utils.InputUtils.*;

/**
 * Apps managing bank accounts.
 * Allows the account's creation, make deposit/ Withdrawal, and checking the balance.
 */
public class BankApplication {
	private static final Logger logger = LogManager.getLogger(BankApplication.class);
	private static final Scanner scanner = new Scanner(System.in);
	private static final Map<Long, BankAccount> accounts = new HashMap<>();



	public static void main(String[] args) {
		while (true) {
			System.out.println("""
 \n--- Bank operations ---
 1. Create a new checking account
 2. Create a new savings account
 3. make a deposit
 4. make a withdrawal
 5. Calculate interests for savings accounts
 6. Check balance
 7. Quit
 """);

			System.out.print("Your choice : ");
			String choice = scanner.nextLine().trim();

			try {
				switch (choice) {
					case "1" -> createCheckingAccount();
					case "2" -> createSavingsAccount();
					case "3" -> performOperation(true);
					case "4" -> performOperation(false);
					case "5" -> calculateInterests();
					case "6" -> checkBalance();
					case "7" -> {
						logger.info("Quitting the application.");
						return;
					}
					default -> System.out.println("Invalid choice.");
				}
			} catch (Exception e) {
				logger.error("Unexpected error : ", e);
			}
		}
	}

	// Create a checking account
	private static void createCheckingAccount() {
		BankAccount checkingAccount = createAccount("checking");
		accounts.put(checkingAccount.getAccountNumber(),checkingAccount);
		logger.info("Checking account number {} created successfully", checkingAccount.getAccountNumber());
	}

	// Create a savings account
	private static void createSavingsAccount() {
		BankAccount savingAccount = createAccount("savings");
		accounts.put(savingAccount.getAccountNumber(),savingAccount);
		logger.info("Savings account number {} created successfully", savingAccount.getAccountNumber());
	}

	// Create account depending on the option chosen by the user
	private static BankAccount createAccount(String type) {

		long accountNumber = readLong("Account number : ");
		String holderName = readString("Holder's name : ");
		double initialBalance = readDouble("Amount : ");


		BankAccount account = switch (type) {
			case "checking" -> new CheckingAccount(accountNumber, holderName, initialBalance);
			case "savings" -> new SavingsAccount(accountNumber, holderName, initialBalance);
			default -> throw new IllegalStateException("unknown type");
		};

		return account;
	}

	// Perform a deposit or a withdrawal
	private static void performOperation(boolean isDeposit) {

		long accountNumber = readLong("Account number : ");

		BankAccount account = accounts.get(accountNumber);
		if (account == null) {
			logger.warn("Account number {} not found", accountNumber);
			return;
		}

		double amount = readDouble("Amount : ");

		if (isDeposit) account.deposit(amount);
		else account.withdraw(amount);
	}


	private static void calculateInterests() {
		if (!accounts.isEmpty()) {
			if(accounts.values().stream()
					.allMatch(bankAccount -> bankAccount instanceof CheckingAccount))
				logger.info("No savings accounts found.");
			else {
				accounts.values().stream()
						.filter(bankAccount -> bankAccount instanceof SavingsAccount)
						.forEach(BankAccount::calculateInterest);
			}
		} else {
			logger.info("No accounts found.");
		}
	}

	private static void checkBalance() {

		long accountNumber = readLong("Account number : ");

		BankAccount account = accounts.get(accountNumber);
		if (account != null) {
			System.out.printf("Current balance : %.2f%n", account.getBalance());
		} else {
			logger.warn("Account number {} not found", accountNumber);
		}
	};
}
