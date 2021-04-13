package com.tcGroup.trainingCenter.user.controller;

import com.tcGroup.trainingCenter.domain.entity.TrainingPlanTemplate;
import com.tcGroup.trainingCenter.user.service.AccountManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("account")
@RestController
public class AccountController {

    @Autowired
    AccountManagementService accountManagementService;

    @GetMapping("/plans/templates")
    @ResponseBody
    public List<TrainingPlanTemplate> getTrainingPlanTemplates() {
        List<TrainingPlanTemplate> templates = accountManagementService.getTrainingPlans();

        return templates;
    }

}
