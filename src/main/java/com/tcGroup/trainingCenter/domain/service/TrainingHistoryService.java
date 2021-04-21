package com.tcGroup.trainingCenter.domain.service;

import com.tcGroup.trainingCenter.domain.entity.TrainingHistoryData;
import com.tcGroup.trainingCenter.domain.request.TrainingHistoryRequest;

import java.util.List;

public interface TrainingHistoryService {

    List<TrainingHistoryData> getAllDataForAccount(Long accountId);
    Long createTrainingPlan(TrainingHistoryRequest request);
    TrainingHistoryData modifyTrainingPlan(TrainingHistoryRequest request);

}
