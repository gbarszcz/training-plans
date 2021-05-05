package com.tcGroup.trainingCenter.user.controller;

import com.tcGroup.trainingCenter.domain.entity.TrainingHistoryData;
import com.tcGroup.trainingCenter.domain.entity.TrainingPlanTemplateData;
import com.tcGroup.trainingCenter.domain.entity.MeasurementData;
import com.tcGroup.trainingCenter.user.response.AccountTrainingsPlansResponse;
import com.tcGroup.trainingCenter.user.service.AccountManagementService;
import com.tcGroup.trainingCenter.utility.logic.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/measurements")
    @ResponseBody
    public List<MeasurementData> getMeasurementsForAccount() {
        return accountManagementService.getMeasurements();
    }

}
