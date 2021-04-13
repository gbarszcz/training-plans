package com.tcGroup.trainingCenter.domain.service;

import com.tcGroup.trainingCenter.domain.calculators.IndexCalculator;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService implements Calculator {
    private IndexCalculator calculatorName;

    public IndexCalculator getCalculatorIndex() {
        return calculatorName;
    }

    public void setCalculatorName(IndexCalculator calculatorName) {
        this.calculatorName = calculatorName;
    }

    public double calculate(){
        return calculatorName.calculateIndex();
    }
}
