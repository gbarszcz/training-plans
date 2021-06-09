package com.tcGroup.trainingCenter.domain.calculators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorServiceCITest {

    @Test
    public void shouldReturnAValidValue(){
        CalculatorCI calculatorCI = new CalculatorCI(70, 180);
        Assertions.assertEquals(12, calculatorCI.calculateIndex());
    }

    @Test
    public void shouldReturnExceptionWhenInputWeightIsLessThanZero(){
        CalculatorCI calculatorCI = new CalculatorCI(-1, 180);

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, calculatorCI::calculateIndex);

        String expectedMessage = "input value out of range";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldReturnExceptionWhenInputHeightIsLessThanZero() {
        CalculatorCI calculatorCI = new CalculatorCI(70, -1);

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, calculatorCI::calculateIndex);

        String expectedMessage = "input value out of range";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

}