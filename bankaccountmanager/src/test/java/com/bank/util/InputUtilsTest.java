package com.bank.util;

import com.bank.utils.InputUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputUtilsTest {

    @Test
    void testReadLong() {
        System.setIn(new ByteArrayInputStream("12\n".getBytes()));
        assertEquals(12L, InputUtils.readLong("Type a number : "));
        System.setIn(System.in);
    }

    @Test
    void testReadLongKO() {
        String input = "abc\n123\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        long result = InputUtils.readLong("Type a number : ");
        assertEquals(123L, result);
    }

    @Test
    void testReadDouble() {
        System.setIn(new ByteArrayInputStream("1.11\n".getBytes()));
        assertEquals(1.11, InputUtils.readDouble("Type a float : "));
    }

    @Test
    void testReadDoubleKO() {
        String input = "abc\n1.5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        double result = InputUtils.readDouble("Type a float : ");
        assertEquals(1.5, result);
    }
}
