package com.tcGroup.trainingCenter.domain.service.impl;

import com.tcGroup.trainingCenter.domain.dao.ExerciseDAO;
import com.tcGroup.trainingCenter.domain.entity.ExerciseData;
import com.tcGroup.trainingCenter.domain.dao.TrainingSeriesTemplateDAO;
import com.tcGroup.trainingCenter.domain.entity.TrainingSeriesTemplateData;
import com.tcGroup.trainingCenter.domain.request.TrainingPlanTemplateRequest;
import com.tcGroup.trainingCenter.domain.dao.TrainingPlanTemplateDAO;
import com.tcGroup.trainingCenter.domain.entity.TrainingPlanTemplateData;
import com.tcGroup.trainingCenter.domain.request.TrainingSeriesTemplateDTO;
import com.tcGroup.trainingCenter.domain.service.TrainingPlanTemplateService;
import com.tcGroup.trainingCenter.user.dao.AccountDAO;
import com.tcGroup.trainingCenter.user.entity.AccountData;
import com.tcGroup.trainingCenter.utility.logic.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "trainingPlanTemplateService")
public class TrainingPlanTemplateServiceImpl extends AbstractService implements TrainingPlanTemplateService {

    @Autowired
    private TrainingPlanTemplateDAO trainingPlanTemplateDAO;

    @Autowired
    private TrainingSeriesTemplateDAO trainingSeriesTemplateDAO;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private ExerciseDAO exerciseDAO;

    @Override
    @Transactional(readOnly = true)
    public List<TrainingPlanTemplateData> getTrainingPlanTemplatesForAccount() {
        return trainingPlanTemplateDAO.getTrainingPlanTemplatesForAccount(getUserContext().getUserId());
    }

    @Override
    public TrainingPlanTemplateData getTrainingPlanTemplateById(Long id) {
        TrainingPlanTemplateData item = trainingPlanTemplateDAO.getItem(id);
        if (item == null || !item.getAccount().getId().equals(getUserContext().getUserId())) {
            throw new IllegalStateException("No training plan template of given id was found");
        }
        return item;
    }

    @Override
    @Transactional
    public Long addTrainingPlanTemplateForAccount(TrainingPlanTemplateRequest request) {
        TrainingPlanTemplateData template = new TrainingPlanTemplateData();
        AccountData account = accountDAO.getItem(getUserContext().getUserId());
        template.setName(request.getName());
        template.setAccount(account);
        Long item = trainingPlanTemplateDAO.createItem(getUserContext(), template);

        addNewSeries(template, request.getSeriesTemplates());

        return item;
    }

    @Override
    @Transactional
    public TrainingPlanTemplateData modifyTrainingPlanTemplate(TrainingPlanTemplateRequest request) {
        TrainingPlanTemplateData trainingPlanTemplate = getTrainingPlanTemplateById(request.getId());
        List<TrainingSeriesTemplateDTO> requestSeriesTemplatesDTO = request.getSeriesTemplates();
        mapRequestToTrainingSeries(trainingPlanTemplate, requestSeriesTemplatesDTO);
        trainingPlanTemplate.setName(request.getName());
        trainingPlanTemplateDAO.modifyItem(getUserContext(), trainingPlanTemplate);
        return trainingPlanTemplate;
    }

    private void mapRequestToTrainingSeries(TrainingPlanTemplateData trainingPlanTemplate,
                                            List<TrainingSeriesTemplateDTO> requestSeriesTemplatesDTO) {
        List<TrainingSeriesTemplateData> trainingSeries = trainingPlanTemplate.getTrainingSeriesTemplateData();
        List<TrainingSeriesTemplateDTO> newSeries = requestSeriesTemplatesDTO
                .stream()
                .filter(series -> series.getId() == null)
                .collect(Collectors.toList());
        addNewSeries(trainingPlanTemplate, newSeries);

        for (TrainingSeriesTemplateData seriesTemplateData : trainingSeries) {
            Optional<TrainingSeriesTemplateDTO> seriesTemplateDTO = requestSeriesTemplatesDTO
                    .stream()
                    .filter(template -> template.getId().equals(seriesTemplateData.getId()))
                    .findAny();
            if (seriesTemplateDTO.isPresent()) {
                mapSeriesTemplateToSeries(seriesTemplateDTO.get(), seriesTemplateData);
            } else {
                trainingSeriesTemplateDAO.removeItem(getUserContext(), seriesTemplateData);
            }
        }
    }

    private void addNewSeries(TrainingPlanTemplateData trainingPlanTemplate, List<TrainingSeriesTemplateDTO> newSeries) {
        newSeries.forEach(templateDTO -> {
            TrainingSeriesTemplateData trainingSeriesTemplateData = new TrainingSeriesTemplateData();
            trainingSeriesTemplateData.setTrainingTemplate(trainingPlanTemplate);
            trainingSeriesTemplateData.setTrainingUnit(templateDTO.getTrainingUnit());
            ExerciseData exerciseData = exerciseDAO.getItem(templateDTO.getExerciseId());
            trainingSeriesTemplateData.setExercise(exerciseData);
            trainingSeriesTemplateDAO.createItem(getUserContext(), trainingSeriesTemplateData);
        });
    }

    private void mapSeriesTemplateToSeries(TrainingSeriesTemplateDTO trainingSeriesTemplateDTO, TrainingSeriesTemplateData seriesTemplateData) {
        seriesTemplateData.setTrainingUnit(trainingSeriesTemplateDTO.getTrainingUnit());
        ExerciseData exercise = exerciseDAO.getItem(trainingSeriesTemplateDTO.getExerciseId());
        seriesTemplateData.setExercise(exercise);
        trainingSeriesTemplateDAO.modifyItem(getUserContext(), seriesTemplateData);
    }

    @Override
    @Transactional
    public boolean deleteTrainingPlanTemplate(Long id) {
        try {
            TrainingPlanTemplateData item = getTrainingPlanTemplateById(id);
            for (TrainingSeriesTemplateData templateData: item.getTrainingSeriesTemplateData()) {
                trainingSeriesTemplateDAO.removeItem(getUserContext(), templateData);
            }
            trainingPlanTemplateDAO.removeItem(getUserContext(), item);
            return true;
        } catch (IllegalStateException ex) {
            return false;
        }
    }
}
