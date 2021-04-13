package com.tcGroup.trainingCenter.domain.calculators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorWHRTest {

    @Test
    public void shouldReturnAValidValueForTheGivenData(){
        CalculatorWHR calculatorWHR = new CalculatorWHR(90, 100);
        Assertions.assertEquals(0.9, calculatorWHR.calculateIndex());
    }

    @Test
    public void shouldReturnZeroWhenInputWaistIsLessThanZero(){
        CalculatorWHR calculatorWHR = new CalculatorWHR(-30 , 100);
        Assertions.assertEquals(0, calculatorWHR.calculateIndex());
    }

    @Test
    public void shouldReturnZeroWhenInputHipCircumferenceIsLessThanZero(){
        CalculatorWHR calculatorWHR = new CalculatorWHR(90, -40);
        Assertions.assertEquals(0, calculatorWHR.calculateIndex());
    }

}