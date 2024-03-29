package com.tcGroup.trainingCenter.domain.calculators;
/**
 * @params weight must be given in kilograms, height must be expressed in centymeters
 */
public class CalculatorCI implements IndexCalculator {
    private final double weight;
    private final double height;

    public CalculatorCI(double weight, double height) {
        this.weight = weight;
        this.height = height;
    }
    @Override
    public double calculateIndex() {
        if(weight > 20 && height > 100){
            double resultCI =(weight) / (Math.pow(height/100, 3));
            resultCI *= 10;
            resultCI = Math.round(resultCI);
            resultCI /= 10;
            return  resultCI;
        }
        throw new IllegalArgumentException("input value out of range");
    }
}
