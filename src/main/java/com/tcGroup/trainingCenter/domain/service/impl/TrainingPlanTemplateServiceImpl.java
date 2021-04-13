package com.tcGroup.trainingCenter.domain.service.impl;

import com.tcGroup.trainingCenter.domain.dao.ExerciseDAO;
import com.tcGroup.trainingCenter.domain.entity.ExerciseData;
import com.tcGroup.trainingCenter.domain.dao.TrainingSeriesTemplateDAO;
import com.tcGroup.trainingCenter.domain.entity.TrainingSeriesTemplate;
import com.tcGroup.trainingCenter.domain.request.TrainingPlanTemplateRequest;
import com.tcGroup.trainingCenter.domain.dao.TrainingPlanTemplateDAO;
import com.tcGroup.trainingCenter.domain.entity.TrainingPlanTemplate;
import com.tcGroup.trainingCenter.domain.service.TrainingPlanTemplateService;
import com.tcGroup.trainingCenter.user.dao.AccountDAO;
import com.tcGroup.trainingCenter.user.entity.AccountData;
import com.tcGroup.trainingCenter.utility.logic.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public List<TrainingPlanTemplate> getTrainingPlanTemplatesForAccount(long accountId) {
        return trainingPlanTemplateDAO.getTrainingPlanTemplatesForAccount(accountId);
    }

    @Override
    public List<TrainingPlanTemplate> getAllTrainingPlans() {
        return trainingPlanTemplateDAO.getItems();
    }

    @Override
    public TrainingPlanTemplate getTrainingPlanById(long id) {
        return trainingPlanTemplateDAO.getItem(id);
    }

    @Override
    @Transactional
    public Long addTrainingPlanTemplateForAccount(TrainingPlanTemplateRequest request) {
        TrainingPlanTemplate template = new TrainingPlanTemplate();
        AccountData account = accountDAO.getItem(request.getAccountId());
        template.setName(request.getName());
        template.setAccount(account);
        Long item = trainingPlanTemplateDAO.createItem(getUserContext(), template);
        List<TrainingSeriesTemplate> trainingSeriesTemplates =
                request.getSeriesTemplates()
                        .stream()
                        .map(templateDTO -> {
                            TrainingSeriesTemplate trainingSeriesTemplate = new TrainingSeriesTemplate();
                            trainingSeriesTemplate.setTrainingTemplate(trainingPlanTemplateDAO.getItem(item));
                            trainingSeriesTemplate.setTrainingUnit(templateDTO.getTrainingUnit());
                            ExerciseData exerciseData = exerciseDAO.getItem(templateDTO.getExerciseId());
                            trainingSeriesTemplate.setExercise(exerciseData);
                            trainingSeriesTemplateDAO.createItem(getUserContext(), trainingSeriesTemplate);
                            return trainingSeriesTemplate;
                        })
                .collect(Collectors.toList());

        return item;
    }
}
