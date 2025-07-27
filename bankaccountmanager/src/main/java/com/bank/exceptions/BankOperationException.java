package com.bank.exceptions;

/**
 * Generic exception for the operations
 */
public class BankOperationException extends RuntimeException{

    public BankOperationException(String message){
        super(message);
    }
}
