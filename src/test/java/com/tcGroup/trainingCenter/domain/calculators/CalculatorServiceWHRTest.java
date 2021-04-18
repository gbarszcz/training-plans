package com.tcGroup.trainingCenter.domain.calculators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorServiceWHRTest {

    @Test
    public void shouldReturnAValidValueForTheGivenData(){
        CalculatorWHR calculatorWHR = new CalculatorWHR(90, 100);
        Assertions.assertEquals(0.9, calculatorWHR.calculateIndex());
    }

    @Test
    public void shouldReturnExceptionWhenInputWaistIsLessThanZero(){
        CalculatorWHR calculatorWHR = new CalculatorWHR(-30 , 100);

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, calculatorWHR::calculateIndex);

        String expectedMessage = "input value out of range";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldReturnExceptionWhenInputHipCircumferenceIsLessThanZero(){
        CalculatorWHR calculatorWHR = new CalculatorWHR(90, -40);

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, calculatorWHR::calculateIndex);

        String expectedMessage = "input value out of range";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

}