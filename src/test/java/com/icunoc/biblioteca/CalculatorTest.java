package com.icunoc.biblioteca;

import com.icunoc.biblioteca.pruebas.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorTest {
    private Calculator calculator = new Calculator();
    @Test
    public void testSumPositiveValues_withNegativeValues() {

        int result = calculator.sumPositiveValues(-10, -20, -30);
        assertTrue(result == 0);
    }
}
