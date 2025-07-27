package com.bank.application;


import java.util.*;

import com.bank.accounts.BankAccount;
import com.bank.accounts.CheckingAccount;
import com.bank.accounts.SavingsAccount;
import com.bank.exceptions.BankOperationException;
import com.bank.users.User;
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
	private static final Map<Long, BankAccount> bankAccounts = new HashMap<>();
	private static final Map<Long, User> users = new HashMap<>();



	public static void main(String[] args) {
		while (true) {
			System.out.println("""
 \n--- Bank operations ---
 1. Create User
 2. Create a new checking account
 3. Create a new savings account
 4. make a deposit
 5. make a withdrawal
 6. Calculate interests for savings accounts
 7. Check balance
 8. show all user's accounts
 9. Quit
 """);

			System.out.print("Your choice : ");
			String choice = scanner.nextLine().trim();

			try {
				switch (choice) {
					case "1" -> createUser();
					case "2" -> createCheckingAccount();
					case "3" -> createSavingsAccount();
					case "4" -> performOperation(true);
					case "5" -> performOperation(false);
					case "6" -> calculateInterests();
					case "7" -> checkBalance();
					case "8" -> displayUserAccounts();
					case "9" -> {
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

	private static void createUser() {
		long idUser = readLong("User number : ");

		if (users.containsKey(idUser)) {
			logger.error("User already exists.");
			return;
		}

		users.put(idUser, new User(idUser));
		logger.info("User created.");
	}

	// Create a checking account
	private static void createCheckingAccount() {
		BankAccount checkingAccount = createAccount("checking");
		bankAccounts.put(checkingAccount.getAccountNumber(),checkingAccount);
		logger.info("Checking account number {} created successfully", checkingAccount.getAccountNumber());
	}

	// Create a savings account
	private static void createSavingsAccount() {
		BankAccount savingAccount = createAccount("savings");
		bankAccounts.put(savingAccount.getAccountNumber(),savingAccount);
		logger.info("Savings account number {} created successfully", savingAccount.getAccountNumber());
	}

	// Create account depending on the option chosen by the user
	private static BankAccount createAccount(String type) {

		long idUser = readLong("User number : ");

		User user = users.get(idUser);
		if (user == null) {
			throw new BankOperationException("User does not exist.");
		}

		long accountNumber = readLong("Account number : ");
		String holderName = readString("Holder's name : ");
		double initialBalance = readDouble("Amount : ");


		BankAccount account = switch (type) {
			case "checking" -> new CheckingAccount(accountNumber, holderName, initialBalance);
			case "savings" -> new SavingsAccount(accountNumber, holderName, initialBalance);
			default -> throw new IllegalStateException("unknown type");
		};

		user.addAccount(account);
		logger.info("Account {} associated to user {} successfully", account.getAccountNumber(), user.getIdUser());

		return account;
	}

	// Perform a deposit or a withdrawal
	private static void performOperation(boolean isDeposit) {

		long accountNumber = readLong("Account number : ");

		BankAccount account = bankAccounts.get(accountNumber);
		if (account == null) {
			logger.warn("Account number {} not found", accountNumber);
		}

		double amount = readDouble("Amount : ");

		if (isDeposit) account.deposit(amount);
		else account.withdraw(amount);
	}


	private static void calculateInterests() {
		if (!bankAccounts.isEmpty()) {
			if(bankAccounts.values().stream()
					.allMatch(bankAccount -> bankAccount instanceof CheckingAccount))
				logger.info("No savings accounts found.");
			else {
				bankAccounts.values().stream()
						.filter(bankAccount -> bankAccount instanceof SavingsAccount)
						.forEach(BankAccount::calculateInterest);
			}
		} else {
			logger.info("No accounts found.");
		}
	}

	private static void checkBalance() {

		long accountNumber = readLong("Account number : ");

		BankAccount account = bankAccounts.get(accountNumber);
		if (account != null) {
			System.out.printf("Current balance : %.2f%n", account.getBalance());
		} else {
			logger.warn("Account number {} not found", accountNumber);
		}
	};

	private static void displayUserAccounts() {
		long idUser = readLong("User number : ");

		User user = users.get(idUser);
		if (user == null) {
			logger.warn("User does not exist.");
			return;
		}

		System.out.printf("Username : %s\n", user.getIdUser());
		for (BankAccount bankAccount : user.getAccounts()) {
			System.out.printf(bankAccount.toString());
		}
	}
}
