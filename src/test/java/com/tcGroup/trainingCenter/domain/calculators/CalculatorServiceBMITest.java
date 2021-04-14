package com.tcGroup.trainingCenter.domain.calculators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorServiceBMITest {

    @Test
    public void shouldReturnAValidValue(){
        CalculatorBMI calculatorBMI = new CalculatorBMI(80, 1.80);
        Assertions.assertEquals(24.7, calculatorBMI.calculateIndex());
    }

    @Test
    public void shouldReturnExceptionWhenHeightLessThan1Meter() {
        CalculatorBMI calculatorBMI = new CalculatorBMI(80, 0.99);

        try {
            calculatorBMI.calculateIndex();
            Assertions.fail("Exception wasn't thrown!");
        }catch (IllegalArgumentException exception){
            Assertions.assertEquals("input value out of range", exception.getMessage());
        }
    }

    @Test
    public void shouldReturnExceptionWhenWeightLessThan20Kilograms(){
        CalculatorBMI calculatorBMI = new CalculatorBMI(19, 1.80);

        try {
            calculatorBMI.calculateIndex();
            Assertions.fail("Exception wasn't thrown!");
        }catch (IllegalArgumentException exception){
            Assertions.assertEquals("input value out of range", exception.getMessage());
        }
    }
}