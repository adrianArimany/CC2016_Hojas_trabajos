package com.example.calculatorTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.factory.OperationFactory;
import com.example.operations.Addition;
import com.example.operations.Division;
import com.example.operations.Modulus;
import com.example.operations.Multiplication;
import com.example.operations.Operation;
import com.example.operations.Substraction;

public class OperationFactoryTest {

    @Test
    public void testAdditionOperator() {
        Operation<Number> operation = OperationFactory.getOperation("+");
        assertTrue(operation instanceof Addition);
    }

    @Test
    public void testSubtractionOperator() {
        Operation<Number> operation = OperationFactory.getOperation("-");
        assertTrue(operation instanceof Substraction);
    }

    @Test
    public void testMultiplicationOperator() {
        Operation<Number> operation = OperationFactory.getOperation("*");
        assertTrue(operation instanceof Multiplication);
    }

    @Test
    public void testDivisionOperator() {
        Operation<Number> operation = OperationFactory.getOperation("/");
        assertTrue(operation instanceof Division);
    }

    @Test
    public void testModulusOperator() {
        Operation<Number> operation = OperationFactory.getOperation("%");
        assertTrue(operation instanceof Modulus);
    }

    @Test
    public void testUnknownOperator() {
        assertThrows(IllegalArgumentException.class, () -> OperationFactory.getOperation("^"));
    }
}
