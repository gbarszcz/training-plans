package com.tcGroup.trainingCenter.domain.service;

import com.tcGroup.trainingCenter.domain.entity.TrainingHistoryData;
import com.tcGroup.trainingCenter.domain.request.TrainingHistoryRequest;
import com.tcGroup.trainingCenter.utility.ApplicationException;

import java.util.Date;
import java.util.List;

public interface TrainingHistoryService {

    List<TrainingHistoryData> getAllDataForAccount();
    Long createTrainingPlan(TrainingHistoryRequest request);
    TrainingHistoryData modifyTrainingPlan(TrainingHistoryRequest request) throws ApplicationException;
    TrainingHistoryData getTrainingPlan(Long id) throws ApplicationException;
    boolean deleteTrainingPlan(Long id) throws ApplicationException;
    List<TrainingHistoryData> getStatisticsForPeriod(Date from, Date to);
}
