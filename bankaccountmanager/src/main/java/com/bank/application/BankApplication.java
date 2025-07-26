package com.bank.application;


import java.util.*;

import com.bank.accounts.BankAccount;
import com.bank.accounts.CheckingAccount;
import com.bank.accounts.SavingsAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Apps managing bank accounts.
 * Allows the account's creation, make deposit/ Withdrawal, and checking the balance.
 */
public class BankApplication {
	private static final Logger logger = LogManager.getLogger(BankApplication.class);
	private static final Scanner scanner = new Scanner(System.in);


	public static void main(String[] args) {
		while (true) {
			System.out.println("""
 \n--- Menu Banque ---
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
		System.out.print("Account number : ");
		String number = scanner.nextLine();
		System.out.print("Holder's name : ");
		String holder = scanner.nextLine();
		System.out.print("Initial balance : ");
		double balance = Double.parseDouble(scanner.nextLine());

		BankAccount checkingAccount = new CheckingAccount(number, holder, balance);

		logger.info("Checking account number {} created successfully", number);
	}

	// Create a savings account
	private static void createSavingsAccount() {
		System.out.print("Account number : ");
		String number = scanner.nextLine();
		System.out.print("Holder's name : ");
		String holder = scanner.nextLine();
		System.out.print("Initial balance : ");
		double balance = Double.parseDouble(scanner.nextLine());

		BankAccount savingAccount = new SavingsAccount(number, holder, balance);

		logger.info("Saving account number {} created successfully", number);
	}
}
