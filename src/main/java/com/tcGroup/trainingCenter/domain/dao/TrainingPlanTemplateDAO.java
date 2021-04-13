package com.tcGroup.trainingCenter.domain.dao;

import com.tcGroup.trainingCenter.domain.entity.TrainingPlanTemplate;
import com.tcGroup.trainingCenter.domain.repository.TrainingPlanTemplateRepository;
import com.tcGroup.trainingCenter.utility.logic.AbstractDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "trainingPlanTemplateDAO")
public class TrainingPlanTemplateDAO extends AbstractDAO<TrainingPlanTemplate, Long> {

    @Autowired
    protected void setRepository(TrainingPlanTemplateRepository trainingPlanTemplateRepository) {
        super.setRepository(trainingPlanTemplateRepository);
    }

    protected TrainingPlanTemplateRepository getRepository() {
        return (TrainingPlanTemplateRepository) super.getRepository();
    }

    public List<TrainingPlanTemplate> getTrainingPlanTemplatesForAccount(long accountId) {
        return getRepository().findAllByAccount_Id(accountId);
    }

}