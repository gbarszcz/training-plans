package com.tcGroup.trainingCenter.domain.calculators;

/**
 * @params weight must be expressed in kilograms, height must be expressed in centymeters
 */
public class CalculatorBMI implements IndexCalculator {
    private final double weight;
    private final double height;

    public CalculatorBMI(double weight, double height) {
        this.weight = weight;
        this.height = height;
    }

    @Override
    public double calculateIndex() {
        if(weight >= 20 && height >= 100){
            double resultBMI = (weight) / (Math.pow(height/100, 2));
            resultBMI *= 10;
            resultBMI = Math.round(resultBMI);
            resultBMI /= 10;
            return resultBMI;
        }
        throw new IllegalArgumentException("input value out of range");
    }
}
