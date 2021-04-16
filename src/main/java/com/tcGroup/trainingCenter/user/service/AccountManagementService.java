package com.tcGroup.trainingCenter.user.service;

import com.tcGroup.trainingCenter.domain.entity.TrainingPlanTemplateData;
import com.tcGroup.trainingCenter.user.entity.AccountData;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountManagementService extends UserDetailsService {

    // -------------------------- ACCOUNTS -------------------------------------

    AccountData getAccountByEmail(String email);

    List<TrainingPlanTemplateData> getTrainingPlans();
}
