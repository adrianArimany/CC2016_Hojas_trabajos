package com.example.calculatorTest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
            RPNCalculator calculator = new RPNCalculator();
            String expression = "2 3 +";
            assertEquals(5, (int) calculator.evaluate(expression));
        }
    
        @Test
        public void testComplexRPNExpression() {
            RPNCalculator calculator = new RPNCalculator();
            String expression = "2 3 + 4 *";
            assertEquals(20, (int) calculator.evaluate(expression));
        }
    
        @Test
        public void testNotEnoughOperands() {
            RPNCalculator calculator = new RPNCalculator();
            String expression = "2 +";
            Throwable throwable = assertThrows(IllegalStateException.class, () -> calculator.evaluate(expression));
            assertEquals("Not enough operands for operator: +", throwable.getMessage());    

        }

        @Test
        public void testTooManyOperands() {
            RPNCalculator calculator = new RPNCalculator();
            String expression = "2 3 4 +";
            Throwable throwable = assertThrows(IllegalStateException.class, () -> calculator.evaluate(expression));
            assertEquals("Invalid RPN expression. Stack should contain exactly one element at the end.", throwable.getMessage());    
        }

    
        
        @Test
        public void testUnknownOperator() {
            RPNCalculator calculator = new RPNCalculator();
            String expression = "2 3 ^";
            Throwable throwable = assertThrows(IllegalArgumentException.class, () -> calculator.evaluate(expression));
            assertEquals("Invalid token encountered: ^", throwable.getMessage());    
            
        }
}
