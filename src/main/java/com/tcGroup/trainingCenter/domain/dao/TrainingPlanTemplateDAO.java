package com.tcGroup.trainingCenter.domain.dao;

import com.tcGroup.trainingCenter.domain.entity.TrainingPlanTemplateData;
import com.tcGroup.trainingCenter.domain.repository.TrainingPlanTemplateRepository;
import com.tcGroup.trainingCenter.domain.searchCriteria.TrainingPlanTemplatesSearchCriteria;
import com.tcGroup.trainingCenter.utility.logic.AbstractDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component(value = "trainingPlanTemplateDAO")
public class TrainingPlanTemplateDAO extends AbstractDAO<TrainingPlanTemplateData, Long> {

    @Autowired
    protected void setRepository(TrainingPlanTemplateRepository trainingPlanTemplateRepository) {
        super.setRepository(trainingPlanTemplateRepository);
    }

    protected TrainingPlanTemplateRepository getRepository() {
        return (TrainingPlanTemplateRepository) super.getRepository();
    }

    public List<TrainingPlanTemplateData> getTrainingPlanTemplatesForAccount(long accountId) {
        return getRepository().findAllByAccount_Id(accountId);
    }

    public List<TrainingPlanTemplateData> getTrainingPlanTemplatesForAccount(long accountId,
            TrainingPlanTemplatesSearchCriteria searchCriteria) {
        return this.getTrainingPlanTemplatesForAccount(accountId).stream()
                .filter(trainingPlan -> trainingPlan.getName().equals(searchCriteria.getName()))
                .collect(Collectors.toList());
    }

}