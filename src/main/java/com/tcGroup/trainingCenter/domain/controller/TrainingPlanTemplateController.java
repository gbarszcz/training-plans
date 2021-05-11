package com.tcGroup.trainingCenter.domain.controller;

import com.tcGroup.trainingCenter.domain.entity.TrainingPlanTemplateData;
import com.tcGroup.trainingCenter.domain.request.TrainingPlanTemplateRequest;
import com.tcGroup.trainingCenter.domain.service.TrainingPlanTemplateService;
import com.tcGroup.trainingCenter.utility.logic.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("training/plans")
@RestController
public class TrainingPlanTemplateController extends AbstractController {

    @Autowired
    private TrainingPlanTemplateService trainingPlanTemplateService;

    @PostMapping("/add")
    public Long addPlanTemplate(@RequestBody @Valid TrainingPlanTemplateRequest request) {
        return trainingPlanTemplateService.addTrainingPlanTemplateForAccount(request);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public TrainingPlanTemplateData getTrainingPlanTemplate(@PathVariable Long id) {
        return trainingPlanTemplateService.getTrainingPlanTemplateById(id);
    }

    @PutMapping("/modify")
    public TrainingPlanTemplateData modifyPlanTemplate(@RequestBody @Valid TrainingPlanTemplateRequest request) {
        return trainingPlanTemplateService.modifyTrainingPlanTemplate(request);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deletePlanTemplate(@PathVariable Long id) {
        return trainingPlanTemplateService.deleteTrainingPlanTemplate(id);
    }
}
