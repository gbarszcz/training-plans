package com.tcGroup.trainingCenter.domain.calculators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorServiceCITest {

    @Test
    public void shouldReturnAValidValue(){
        CalculatorCI calculatorCI = new CalculatorCI(70, 1.80);
        Assertions.assertEquals(12, calculatorCI.calculateIndex());
    }

    @Test
    public void shouldReturnExceptionWhenInputWeightIsLessThanZero(){
        CalculatorCI calculatorCI = new CalculatorCI(-1, 1.80);

        try {
            calculatorCI.calculateIndex();
            Assertions.fail("Exception wasn't thrown!");
        }catch (IllegalArgumentException exception){
            Assertions.assertEquals("input value out of range", exception.getMessage());
        }

    }

    @Test
    public void shouldReturnExceptionWhenInputHeightIsLessThanZero() {
        CalculatorCI calculatorCI = new CalculatorCI(70, -1);

        try {
            calculatorCI.calculateIndex();
            Assertions.fail("Exception wasn't thrown!");
        } catch (IllegalArgumentException exception) {
            Assertions.assertEquals("input value out of range", exception.getMessage());
        }
    }

}