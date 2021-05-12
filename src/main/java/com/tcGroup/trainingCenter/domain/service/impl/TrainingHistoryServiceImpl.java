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
import java.util.*;

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

    @Override
    public List<TrainingHistoryData> getAllDataForAccount() {
        return trainingHistoryDAO.findByAccountId(getUserContext().getUserId());
    }

    @Override
    @Transactional
    public Long createTrainingPlan(TrainingHistoryRequest request) {
        AccountData account = accountDAO.getItem(getUserContext().getUserId());
        TrainingPlanTemplateData template = trainingPlanTemplateDAO.getItem(request.getTemplateId());

        TrainingHistoryData training = new TrainingHistoryData();
        training.setTitle(request.getTitle());
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
        TrainingHistoryData training = this.getTrainingPlan(request.getId());
        List<TrainingSeriesDataDTO> trainingSeriesDataList = request.getTrainingSeriesData();
        mapSeriesDataListToSeriesData(training, trainingSeriesDataList);
        training.setTitle(request.getTitle());
        training.setTrainingDate(Date.from(request.getTrainingDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

        trainingHistoryDAO.modifyItem(getUserContext(), training);
        return training;
    }

    private void mapSeriesDataListToSeriesData(TrainingHistoryData training, List<TrainingSeriesDataDTO> trainingSeriesDataList) {
        double difficultySum = 0d;

        for (TrainingSeriesDataDTO seriesDataDTO : trainingSeriesDataList) {
            TrainingSeriesData trainingSeriesData = mapToSeriesData(seriesDataDTO, training.getTrainingDate());
            difficultySum += trainingSeriesData.getExercise().getExerciseDifficultyLvl().getLevel();
            trainingSeriesDAO.modifyItem(getUserContext(), trainingSeriesData);
        }
        Optional<DifficultyLevel> difficultyLevel = calculateTrainingDifficulty(difficultySum, trainingSeriesDataList.size());
        difficultyLevel.ifPresent(training::setDifficulty);
    }

    private Optional<DifficultyLevel> calculateTrainingDifficulty(double difficultySum, int exercisesCount) {
        Optional<DifficultyLevel> difficultyLevel = Optional.empty();
        if (difficultySum != 0 && exercisesCount > 0) {
            double difficulty = Math.ceil(difficultySum / exercisesCount);
            difficultyLevel = Arrays.stream(DifficultyLevel.values()).filter(d -> d.getLevel() == difficulty).findAny();
        }
        return difficultyLevel;
    }

    @Override
    public TrainingHistoryData getTrainingPlan(Long id) {
        TrainingHistoryData item = trainingHistoryDAO.getItem(id);
        if (item == null || !item.getAccount().getId().equals(getUserContext().getUserId())) {
            throw new IllegalStateException("No training plan of given id was found");
        }
        return item;
    }

    @Override
    @Transactional
    public boolean deleteTrainingPlan(Long id) {
        try {
            TrainingHistoryData item = getTrainingPlan(id);
            for (TrainingSeriesData series : item.getTrainingSeriesData()) {
                TrainingSeriesResultData trainingSeriesResultData = series.getTrainingSeriesResultData();
                trainingSeriesResultDAO.removeItem(getUserContext(), trainingSeriesResultData);
                trainingSeriesDAO.removeItem(getUserContext(), series);
            }
            trainingHistoryDAO.removeItem(getUserContext(), item);
            return true;
        } catch (IllegalStateException ex) {
            return false;
        }
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
