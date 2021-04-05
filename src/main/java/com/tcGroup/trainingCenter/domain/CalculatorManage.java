package com.tcGroup.trainingCenter.domain;

import com.tcGroup.trainingCenter.domain.calculators.IIndexCalculator;

public class CalculatorManage {
    private IIndexCalculator calculatorName;

    public IIndexCalculator getCalculatorIndex() {
        return calculatorName;
    }

    public void setCalculatorName(IIndexCalculator calculatorName) {
        this.calculatorName = calculatorName;
    }

    public double calculate(){
        return calculatorName.calculateIndex();
    }
}
