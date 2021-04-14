package com.tcGroup.trainingCenter.domain.service;

import com.tcGroup.trainingCenter.domain.request.TrainingPlanTemplateRequest;
import com.tcGroup.trainingCenter.domain.entity.TrainingPlanTemplateData;

import java.util.List;

public interface TrainingPlanTemplateService {

    List<TrainingPlanTemplateData> getTrainingPlanTemplatesForAccount(long accountId);
    List<TrainingPlanTemplateData> getAllTrainingPlans();
    TrainingPlanTemplateData getTrainingPlanById(long id);
    Long addTrainingPlanTemplateForAccount(TrainingPlanTemplateRequest request);

}
