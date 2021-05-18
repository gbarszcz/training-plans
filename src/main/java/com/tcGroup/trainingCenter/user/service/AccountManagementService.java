package com.tcGroup.trainingCenter.user.service;

import com.tcGroup.trainingCenter.domain.entity.TrainingHistoryData;
import com.tcGroup.trainingCenter.domain.entity.MeasurementData;
import com.tcGroup.trainingCenter.domain.entity.TrainingPlanTemplateData;
import com.tcGroup.trainingCenter.user.entity.AccountData;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountManagementService extends UserDetailsService {

    // -------------------------- ACCOUNTS -------------------------------------

    AccountData getAccountByEmail(String email);

    AccountData getAccountById(Long id);

    Long modifyAccount(AccountData accountData);

    List<TrainingPlanTemplateData> getTrainingPlans();

    List<TrainingHistoryData> getTrainingHistory();

}
