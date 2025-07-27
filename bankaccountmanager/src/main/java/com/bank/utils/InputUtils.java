package com.bank.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;


public class InputUtils {
    private static final Logger logger = LogManager.getLogger(InputUtils.class);

    public static long readLong(String message) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            try {
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                logger.error("Account number must number.");
            }
        }
    }

    public static double readDouble(String message) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                logger.error("The amount must a float.");
            }
        }
    }

    public static String readString(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        return scanner.nextLine();
    }
}