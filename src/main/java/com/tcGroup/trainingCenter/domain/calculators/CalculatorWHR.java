package com.tcGroup.trainingCenter.domain.calculators;

/**
 * @params waist and hipCircumference must be expressed in equal units in centimeters
 */
public class CalculatorWHR implements IndexCalculator {
    private final double waist;
    private final double hipCircumference;

    public CalculatorWHR(double waist, double hipCircumference) {
        this.waist = waist;
        this.hipCircumference = hipCircumference;
    }

    @Override
    public double calculateIndex() {
        if(waist>0 && hipCircumference >0){
            double result = waist / hipCircumference;
            result*=10;
            result = Math.round(result);
            result/=10;
            return  result;
        }
        throw new IllegalArgumentException("input value out of range");
    }
}
