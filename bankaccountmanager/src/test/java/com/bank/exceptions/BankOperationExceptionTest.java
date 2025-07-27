package com.bank.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankOperationExceptionTest {

    @Test
    public void testMessage(){
        BankOperationException bankOperationException = new BankOperationException("operation error.");
        assertEquals("operation error.", bankOperationException.getMessage());
    }


}
