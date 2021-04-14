package com.tcGroup.trainingCenter.domain.service.impl;

import com.tcGroup.trainingCenter.domain.calculators.IndexCalculator;
import com.tcGroup.trainingCenter.domain.service.CalculatorService;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceServiceIml implements CalculatorService {
    private IndexCalculator calculator;

    public IndexCalculator getCalculatorIndex() {
        return calculator;
    }

    public void setCalculatorName(IndexCalculator calculatorName) {
        this.calculator = calculatorName;
    }

    public double calculate(){
        return calculator.calculateIndex();
    }
}
