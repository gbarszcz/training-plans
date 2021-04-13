package com.tcGroup.trainingCenter.domain.service;

import com.tcGroup.trainingCenter.domain.request.TrainingPlanTemplateRequest;
import com.tcGroup.trainingCenter.domain.entity.TrainingPlanTemplate;

import java.util.List;

public interface TrainingPlanTemplateService {

    List<TrainingPlanTemplate> getTrainingPlanTemplatesForAccount(long accountId);
    List<TrainingPlanTemplate> getAllTrainingPlans();
    TrainingPlanTemplate getTrainingPlanById(long id);
    Long addTrainingPlanTemplateForAccount(TrainingPlanTemplateRequest request);

}
