package com.tcGroup.trainingCenter.domain.calculators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorBMITest {

    @Test
    public void shouldReturnAValidValue(){
        CalculatorBMI calculatorBMI = new CalculatorBMI(80, 1.80);
        Assertions.assertEquals(24.7, calculatorBMI.calculateIndex());
    }

    @Test
    public void shouldReturnZeroWhenHeightLessThan100Centimeters() {
        CalculatorBMI calculatorBMI = new CalculatorBMI(80, 0.99);
        Assertions.assertEquals(0, calculatorBMI.calculateIndex());
    }

    @Test
    public void shouldReturnZeroWhenWeightLessThan20Kilograms(){
        CalculatorBMI calculatorBMI = new CalculatorBMI(19, 1.80);
        Assertions.assertEquals(0, calculatorBMI.calculateIndex());
    }
}