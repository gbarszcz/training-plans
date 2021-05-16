package com.tcGroup.trainingCenter.domain.controller;

import com.tcGroup.trainingCenter.domain.entity.TrainingPlanTemplateData;
import com.tcGroup.trainingCenter.domain.request.TrainingPlanTemplateRequest;
import com.tcGroup.trainingCenter.domain.service.TrainingPlanTemplateService;
import com.tcGroup.trainingCenter.utility.ApplicationException;
import com.tcGroup.trainingCenter.utility.logic.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RequestMapping("training/plan")
@RestController
public class TrainingPlanTemplateController extends AbstractController {

    @Autowired
    private TrainingPlanTemplateService trainingPlanTemplateService;

    @PostMapping
    public Long addPlanTemplate(@RequestBody @Valid TrainingPlanTemplateRequest request) {
        return trainingPlanTemplateService.addTrainingPlanTemplateForAccount(request);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public TrainingPlanTemplateData getTrainingPlanTemplate(@PathVariable Long id) {
        try {
            return trainingPlanTemplateService.getTrainingPlanTemplateById(id);
        } catch (ApplicationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PutMapping
    public TrainingPlanTemplateData modifyPlanTemplate(@RequestBody @Valid TrainingPlanTemplateRequest request) {
        try {
            return trainingPlanTemplateService.modifyTrainingPlanTemplate(request);
        } catch (ApplicationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public boolean deletePlanTemplate(@PathVariable Long id) {
        try {
            return trainingPlanTemplateService.deleteTrainingPlanTemplate(id);
        } catch (ApplicationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
