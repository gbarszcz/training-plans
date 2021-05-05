package com.tcGroup.trainingCenter.domain.controller;


import com.tcGroup.trainingCenter.domain.calculators.CalculatorBMI;
import com.tcGroup.trainingCenter.domain.calculators.CalculatorCI;
import com.tcGroup.trainingCenter.domain.calculators.CalculatorWHR;
import com.tcGroup.trainingCenter.domain.enumeration.CalculatorType;
import com.tcGroup.trainingCenter.domain.service.CalculatorService;
import com.tcGroup.trainingCenter.utility.logic.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/calculate", produces = "application/json")
public class CalculatorController extends AbstractController {


    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping
    public ResponseEntity<Double> calculateIndex(@RequestParam("calculatorType") CalculatorType type,
                                                 @RequestParam(required = false) Double weight, @RequestParam(required = false) Double height,
                                                 @RequestParam(required = false) Double waist, @RequestParam(required = false) Double hipCircumference){

        if(type.equals(CalculatorType.BMI)){
            calculatorService.setCalculatorName(new CalculatorBMI(weight, height));
            return new ResponseEntity<>(calculatorService.calculate() , HttpStatus.OK);
        }
        else if(type.equals(CalculatorType.CI)){
            calculatorService.setCalculatorName(new CalculatorCI(weight, height));
            return new ResponseEntity<>(calculatorService.calculate() , HttpStatus.OK);
        }
        else if(type.equals(CalculatorType.WHR)){
            calculatorService.setCalculatorName(new CalculatorWHR(waist, hipCircumference));
            return new ResponseEntity<>(calculatorService.calculate(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
