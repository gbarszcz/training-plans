package com.tcGroup.trainingCenter.domain.calculators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorCITest {

    @Test
    public void shouldReturnAValidValue(){
        CalculatorCI calculatorCI = new CalculatorCI(70, 1.80);
        Assertions.assertEquals(12, calculatorCI.calculateIndex());
    }

    @Test
    public void shouldReturnZeroWhenInputWeightIsLessThanZero(){
        CalculatorCI calculatorCI = new CalculatorCI(-1, 1.80);
        Assertions.assertEquals(0, calculatorCI.calculateIndex());
    }

    @Test
    public void shouldReturnZeroWhenInputHeightIsLessThanZero(){
        CalculatorCI calculatorCI = new CalculatorCI(70, -1);
        Assertions.assertEquals(0, calculatorCI.calculateIndex());
    }

}