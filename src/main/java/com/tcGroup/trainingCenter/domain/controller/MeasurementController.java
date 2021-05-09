package com.tcGroup.trainingCenter.domain.controller;

import com.tcGroup.trainingCenter.domain.entity.MeasurementData;
import com.tcGroup.trainingCenter.domain.service.MeasurementService;
import com.tcGroup.trainingCenter.utility.logic.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("measurement")
@RestController
public class MeasurementController extends AbstractController {

    @Autowired
    private MeasurementService measurementService;

    @GetMapping("/{id}")
    public MeasurementData getMeasurementData(@PathVariable Long id) {
        return measurementService.getById(id);
    }

    @PostMapping("/add")
    public Long addMeasurementData(@RequestBody @Valid MeasurementData request) {
        return measurementService.addMeasurement(request);
    }

    @PutMapping("/modify")
    @ResponseBody
    public MeasurementData modifyMeasurementData(@RequestBody @Valid MeasurementData request) {
        return measurementService.modifyMeasurement(request);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteMeasurementData(@PathVariable Long id) {
        return measurementService.deleteMeasurement(id);
    }

}
