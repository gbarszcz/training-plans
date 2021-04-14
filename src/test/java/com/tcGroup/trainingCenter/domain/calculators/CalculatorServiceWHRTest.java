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

        try{
            calculatorWHR.calculateIndex();
            Assertions.fail("Exception wasn't thrown!");
        } catch (IllegalArgumentException exception) {
            Assertions.assertEquals("input value out of range", exception.getMessage());
        }

    }

    @Test
    public void shouldReturnExceptionWhenInputHipCircumferenceIsLessThanZero(){
        CalculatorWHR calculatorWHR = new CalculatorWHR(90, -40);

        try{
            calculatorWHR.calculateIndex();
            Assertions.fail("Exception wasn't thrown!");

        } catch (IllegalArgumentException exception) {
            Assertions.assertEquals("input value out of range", exception.getMessage());
        }

    }

}