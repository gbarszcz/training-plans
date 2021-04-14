package com.tcGroup.trainingCenter.domain.service;

import com.tcGroup.trainingCenter.domain.calculators.IndexCalculator;

public interface CalculatorService {
     double calculate();
     void setCalculatorName(IndexCalculator calculatorName);
}
