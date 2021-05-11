package com.tcGroup.trainingCenter.domain.service;

import com.tcGroup.trainingCenter.domain.request.TrainingPlanTemplateRequest;
import com.tcGroup.trainingCenter.domain.entity.TrainingPlanTemplateData;

import java.util.List;

public interface TrainingPlanTemplateService {

    List<TrainingPlanTemplateData> getTrainingPlanTemplatesForAccount();
    TrainingPlanTemplateData getTrainingPlanTemplateById(Long id);
    Long addTrainingPlanTemplateForAccount(TrainingPlanTemplateRequest request);
    TrainingPlanTemplateData modifyTrainingPlanTemplate(TrainingPlanTemplateRequest request);
    boolean deleteTrainingPlanTemplate(Long id);

}
