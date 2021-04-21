package com.tcGroup.trainingCenter.user.controller;

import com.tcGroup.trainingCenter.domain.entity.TrainingPlanTemplateData;
import com.tcGroup.trainingCenter.user.service.AccountManagementService;
import com.tcGroup.trainingCenter.utility.logic.AbstractController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("account")
@RestController
public class AccountController extends AbstractController {

    @Autowired
    AccountManagementService accountManagementService;

    @GetMapping("/plans/templates")
    @ResponseBody
    public List<TrainingPlanTemplateData> getTrainingPlanTemplates() {
        return accountManagementService.getTrainingPlans();
    }

}
