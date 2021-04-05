package com.tcGroup.trainingCenter.domain.calculators;

/**
 * @params weight must be expressed in kilograms, height must be expressed in meters
 */
public class CalculatorBMI implements IIndexCalculator{
    private final double weight;
    private final double height;

    public CalculatorBMI(double weight, double height) {
        this.weight = weight;
        this.height = height;
    }

    @Override
    public double calculateIndex() {
        if(weight > 0 && height > 0){
            double resultBMI = (weight) / (Math.pow(height, 2));
            resultBMI *= 10;
            resultBMI = Math.round(resultBMI);
            resultBMI /= 10;
            return resultBMI;
        }
        return 0;
    }
}
