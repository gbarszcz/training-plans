package com.tcGroup.trainingCenter.domain.service;

import com.tcGroup.trainingCenter.domain.request.TrainingPlanTemplateRequest;
import com.tcGroup.trainingCenter.domain.entity.TrainingPlanTemplateData;
import com.tcGroup.trainingCenter.utility.ApplicationException;

import java.util.List;

public interface TrainingPlanTemplateService {

    List<TrainingPlanTemplateData> getTrainingPlanTemplatesForAccount();
    TrainingPlanTemplateData getTrainingPlanTemplateById(Long id) throws ApplicationException;
    Long addTrainingPlanTemplateForAccount(TrainingPlanTemplateRequest request);
    TrainingPlanTemplateData modifyTrainingPlanTemplate(TrainingPlanTemplateRequest request) throws ApplicationException;
    boolean deleteTrainingPlanTemplate(Long id) throws ApplicationException;

}
