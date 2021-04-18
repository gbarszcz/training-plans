package com.tcGroup.trainingCenter.domain.dao;

import com.tcGroup.trainingCenter.domain.entity.TrainingSeriesData;
import com.tcGroup.trainingCenter.domain.repository.TrainingSeriesRepository;
import com.tcGroup.trainingCenter.utility.logic.AbstractDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("trainingSeriesDAO")
public class TrainingSeriesDAO extends AbstractDAO<TrainingSeriesData, Long> {

    @Autowired
    protected void setRepository(TrainingSeriesRepository repository) {
        super.setRepository(repository);
    }

    protected TrainingSeriesRepository getRepository() {
        return (TrainingSeriesRepository) super.getRepository();
    }


}
