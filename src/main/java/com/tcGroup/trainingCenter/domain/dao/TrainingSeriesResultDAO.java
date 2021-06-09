package com.tcGroup.trainingCenter.domain.dao;

import com.tcGroup.trainingCenter.domain.entity.TrainingSeriesResultData;
import com.tcGroup.trainingCenter.domain.repository.TrainingSeriesResultRepository;
import com.tcGroup.trainingCenter.utility.logic.AbstractDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("trainingSeriesResultDAO")
public class TrainingSeriesResultDAO extends AbstractDAO<TrainingSeriesResultData, Long> {

    @Autowired
    protected void setRepository(TrainingSeriesResultRepository repository) {
        super.setRepository(repository);
    }

    protected TrainingSeriesResultRepository getRepository() {
        return (TrainingSeriesResultRepository) super.getRepository();
    }

}
