package com.tcGroup.trainingCenter.domain.service.impl;

import com.tcGroup.trainingCenter.domain.dao.ExerciseDAO;
import com.tcGroup.trainingCenter.domain.entity.ExerciseData;
import com.tcGroup.trainingCenter.domain.dao.TrainingSeriesTemplateDAO;
import com.tcGroup.trainingCenter.domain.entity.TrainingSeriesTemplateData;
import com.tcGroup.trainingCenter.domain.request.TrainingPlanTemplateRequest;
import com.tcGroup.trainingCenter.domain.dao.TrainingPlanTemplateDAO;
import com.tcGroup.trainingCenter.domain.entity.TrainingPlanTemplateData;
import com.tcGroup.trainingCenter.domain.service.TrainingPlanTemplateService;
import com.tcGroup.trainingCenter.user.dao.AccountDAO;
import com.tcGroup.trainingCenter.user.entity.AccountData;
import com.tcGroup.trainingCenter.utility.logic.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public TrainingPlanTemplateData getTrainingPlanById(long id) {
        return trainingPlanTemplateDAO.getItem(id);
    }

    @Override
    @Transactional
    public Long addTrainingPlanTemplateForAccount(TrainingPlanTemplateRequest request) {
        TrainingPlanTemplateData template = new TrainingPlanTemplateData();
        AccountData account = accountDAO.getItem(getUserContext().getUserId());
        template.setName(request.getName());
        template.setAccount(account);
        Long item = trainingPlanTemplateDAO.createItem(getUserContext(), template);

        request.getSeriesTemplates()
                .forEach(templateDTO -> {
                    TrainingSeriesTemplateData trainingSeriesTemplateData = new TrainingSeriesTemplateData();
                    trainingSeriesTemplateData.setTrainingTemplate(trainingPlanTemplateDAO.getItem(item));
                    trainingSeriesTemplateData.setTrainingUnit(templateDTO.getTrainingUnit());
                    ExerciseData exerciseData = exerciseDAO.getItem(templateDTO.getExerciseId());
                    trainingSeriesTemplateData.setExercise(exerciseData);
                    trainingSeriesTemplateDAO.createItem(getUserContext(), trainingSeriesTemplateData);
                });

        return item;
    }
}
