package com.tcGroup.trainingCenter.domain.service.impl;

import com.tcGroup.trainingCenter.domain.dao.*;
import com.tcGroup.trainingCenter.domain.entity.*;
import com.tcGroup.trainingCenter.domain.enumeration.DifficultyLevel;
import com.tcGroup.trainingCenter.domain.enumeration.IterationUnit;
import com.tcGroup.trainingCenter.domain.request.TrainingHistoryRequest;
import com.tcGroup.trainingCenter.domain.request.TrainingSeriesDataDTO;
import com.tcGroup.trainingCenter.domain.request.TrainingSeriesResultDataDTO;
import com.tcGroup.trainingCenter.domain.service.TrainingHistoryService;
import com.tcGroup.trainingCenter.user.dao.AccountDAO;
import com.tcGroup.trainingCenter.user.entity.AccountData;
import com.tcGroup.trainingCenter.utility.logic.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(value = "trainingHistoryService")
public class TrainingHistoryServiceImpl extends AbstractService implements TrainingHistoryService {

    @Autowired
    private TrainingHistoryDAO trainingHistoryDAO;

    @Autowired
    private TrainingSeriesDAO trainingSeriesDAO;

    @Autowired
    private TrainingSeriesResultDAO trainingSeriesResultDAO;

    @Autowired
    private TrainingPlanTemplateDAO trainingPlanTemplateDAO;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private ExerciseDAO exerciseDAO;

    public List<TrainingHistoryData> getAllDataForAccount(Long accountId) {
        return trainingHistoryDAO.findByAccountId(accountId);
    }

    @Override
    @Transactional
    public Long createTrainingPlan(TrainingHistoryRequest request) {
        AccountData account = accountDAO.getItem(request.getAccountId());
        TrainingPlanTemplateData template = trainingPlanTemplateDAO.getItem(request.getTemplateId());

        TrainingHistoryData training = new TrainingHistoryData();
        training.setTrainingDate(Date.from(request.getTrainingDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        training.setAccount(account);

        Long historyId = trainingHistoryDAO.createItem(getUserContext(), training);
        mapSeriesTemplateToTrainingHistorySeries(training, template);
        return historyId;
    }

    private List<TrainingSeriesData> mapSeriesTemplateToTrainingHistorySeries(TrainingHistoryData training, TrainingPlanTemplateData template) {
        List<TrainingSeriesData> trainingSeriesData = new ArrayList<>();
        for (TrainingSeriesTemplateData series : template.getTrainingSeriesTemplateData()) {
            TrainingSeriesData seriesData = new TrainingSeriesData();
            seriesData.setExercise(series.getExercise());
            seriesData.setTrainingUnit(series.getTrainingUnit());
            seriesData.setTraining(training);

            TrainingSeriesResultData trainingSeriesResultData = new TrainingSeriesResultData();
            trainingSeriesResultDAO.createItem(getUserContext(), trainingSeriesResultData);
            seriesData.setTrainingSeriesResultData(trainingSeriesResultData);

            trainingSeriesDAO.createItem(getUserContext(), seriesData);
            trainingSeriesData.add(seriesData);
        }
        return trainingSeriesData;
    }

    @Transactional
    public TrainingHistoryData modifyTrainingPlan(TrainingHistoryRequest request) {
        TrainingHistoryData training = trainingHistoryDAO.getItem(request.getId());
        for (TrainingSeriesDataDTO seriesDataDTO : request.getTrainingSeriesData()) {
            TrainingSeriesData trainingSeriesData = mapToSeriesData(seriesDataDTO, training.getTrainingDate());
            trainingSeriesDAO.modifyItem(getUserContext(), trainingSeriesData);
        }
        training.setTrainingDate(Date.from(request.getTrainingDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        trainingHistoryDAO.modifyItem(getUserContext(), training);
        return training;
    }

    private TrainingSeriesData mapToSeriesData(TrainingSeriesDataDTO seriesDataDTO, Date trainingDate) {
        TrainingSeriesData trainingSeriesData = trainingSeriesDAO.getItem(seriesDataDTO.getId());
        trainingSeriesData.setTrainingUnit(seriesDataDTO.getTrainingUnit());

        ExerciseData exerciseData = exerciseDAO.getItem(seriesDataDTO.getExerciseId());
        trainingSeriesData.setExercise(exerciseData);

        TrainingSeriesResultData trainingSeriesResultData = mapToResultData(seriesDataDTO.getTrainingSeriesResultData());
        Double result = calculateResult(trainingDate, trainingSeriesResultData);
        trainingSeriesResultData.setResult(result);
        trainingSeriesResultDAO.modifyItem(getUserContext(), trainingSeriesResultData);
        trainingSeriesData.setTrainingSeriesResultData(trainingSeriesResultData);

        return trainingSeriesData;
    }

    private TrainingSeriesResultData mapToResultData(TrainingSeriesResultDataDTO resultDTO) {
        TrainingSeriesResultData trainingSeriesResultData = trainingSeriesResultDAO.getItem(resultDTO.getId());
        trainingSeriesResultData.setIterationUnit(IterationUnit.valueOf(resultDTO.getIterationUnit()));
        trainingSeriesResultData.setIterationCount(resultDTO.getIterationCount());
        trainingSeriesResultData.setAdditionalWeight(resultDTO.getAdditionalWeight());
        return trainingSeriesResultData;
    }

    private Double calculateResult(Date trainingDate, TrainingSeriesResultData resultData) {
        return 75.5
// TODO userContext() should be used here, but the endpoint can be called without any authorization
//        return (getUserContext().getUserWeightByDate(exerciseDate)
                + resultData.getAdditionalWeight()
                * resultData.getIterationCount();
    }
}
