package com.tcGroup.trainingCenter.user.controller;

import java.util.List;

import com.tcGroup.trainingCenter.domain.entity.TrainingHistoryData;
import com.tcGroup.trainingCenter.domain.entity.TrainingPlanTemplateData;
import com.tcGroup.trainingCenter.domain.searchCriteria.TrainingPlanTemplatesSearchCriteria;
import com.tcGroup.trainingCenter.user.response.AccountTrainingsPlansResponse;
import com.tcGroup.trainingCenter.user.service.AccountManagementService;
import com.tcGroup.trainingCenter.utility.logic.AbstractController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("account")
@RestController
public class AccountController extends AbstractController {

    @Autowired
    AccountManagementService accountManagementService;

    @GetMapping("/trainings-plans")
    @ResponseBody
    public AccountTrainingsPlansResponse getTrainingsAndTemplates() {
        List<TrainingPlanTemplateData> trainingPlans = accountManagementService.getTrainingPlans();
        List<TrainingHistoryData> trainingHistory = accountManagementService.getTrainingHistory();
        return new AccountTrainingsPlansResponse(trainingHistory, trainingPlans);
    }

    @PostMapping("/training-plans")
    @ResponseBody
    public AccountTrainingsPlansResponse getTrainingsAndTemplates(@RequestBody TrainingPlanTemplatesSearchCriteria searchCriteria) {
        List<TrainingPlanTemplateData> trainingPlans = accountManagementService.getTrainingPlans(searchCriteria);
        List<TrainingHistoryData> trainingHistory = accountManagementService.getTrainingHistory();
        return new AccountTrainingsPlansResponse(trainingHistory, trainingPlans);
    }
}
