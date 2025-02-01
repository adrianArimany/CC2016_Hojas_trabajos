package com.example.calculatorTest;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import com.example.object_calculadora.RPNCalculator;
/**
 * @Todo 
 * 
 * 1. If a file is found with a letter remove it from the line (partially FIXED, as is fixed in the system as another class fixes it for RPNCalculator, but I wasn't able to fix it in RPNCalculator)
 * 
 */

public class RPNCalculatorTest {
    
        @Test
        public void testSimpleRPNExpression() {
            RPNCalculator<Integer> calculator = new RPNCalculator<>(Integer.class);
            String expression = "2 3 +";
            assertEquals(5, (int) calculator.evaluate(expression));
        }
    
        @Test
        public void testComplexRPNExpression() {
            RPNCalculator<Integer> calculator = new RPNCalculator<>(Integer.class);
            String expression = "2 3 + 4 *";
            assertEquals(20, (int) calculator.evaluate(expression));
        }
    
        @Test
        public void testNotEnoughOperands() {
            RPNCalculator<Integer> calculator = new RPNCalculator<>(Integer.class);
            String expression = "2 +";
            assertThrows(IllegalStateException.class, () -> calculator.evaluate(expression));
        }
    
        @Test
        public void testTooManyOperands() {
            RPNCalculator<Integer> calculator = new RPNCalculator<>(Integer.class);
            String expression = "2 3 4 +";
            assertThrows(IllegalStateException.class, () -> calculator.evaluate(expression));
        }
    
        @Test
        public void testNonNumericToken() {
            RPNCalculator<Integer> calculator = new RPNCalculator<>(Integer.class);
            String expression = " a 2 +";
            assertThrows(NumberFormatException.class, () -> calculator.evaluate(expression));
        } //I wansn't able to fix it in this class, but I was able to fix it fileReaderUtil.
    
        @Test
        public void testUnknownOperator() {
            RPNCalculator<Integer> calculator = new RPNCalculator<>(Integer.class);
            String expression = "2 3 ^";
            assertThrows(IllegalArgumentException.class, () -> calculator.evaluate(expression));
        }
}
