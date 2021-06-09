package com.tcGroup.trainingCenter.domain.calculators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorServiceBMITest {

    @Test
    public void shouldReturnAValidValue(){
        CalculatorBMI calculatorBMI = new CalculatorBMI(80, 180);
        Assertions.assertEquals(24.7, calculatorBMI.calculateIndex());
    }

    @Test
    public void shouldReturnExceptionWhenHeightLessThan1Meter() {
        CalculatorBMI calculatorBMI = new CalculatorBMI(70, 99);

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, calculatorBMI::calculateIndex);

        String expectedMessage = "input value out of range";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldReturnExceptionWhenWeightLessThan20Kilograms(){
        CalculatorBMI calculatorBMI = new CalculatorBMI(19, 180);

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, calculatorBMI::calculateIndex);

        String expectedMessage = "input value out of range";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}