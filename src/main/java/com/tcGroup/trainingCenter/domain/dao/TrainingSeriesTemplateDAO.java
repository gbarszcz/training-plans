package com.tcGroup.trainingCenter.domain.dao;

import com.tcGroup.trainingCenter.domain.entity.TrainingSeriesTemplate;
import com.tcGroup.trainingCenter.domain.repository.TrainingSeriesTemplateRepository;
import com.tcGroup.trainingCenter.utility.logic.AbstractDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "trainingSeriesTemplateDAO")
public class TrainingSeriesTemplateDAO extends AbstractDAO<TrainingSeriesTemplate, Long> {

    @Autowired
    protected void setRepository(TrainingSeriesTemplateRepository trainingSeriesTemplateRepository) {
        super.setRepository(trainingSeriesTemplateRepository);
    }

    protected TrainingSeriesTemplateRepository getRepository() {
        return (TrainingSeriesTemplateRepository) super.getRepository();
    }

}