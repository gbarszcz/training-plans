package com.tcGroup.trainingCenter.domain.dao;

import com.tcGroup.trainingCenter.domain.entity.TrainingHistoryData;
import com.tcGroup.trainingCenter.domain.repository.TrainingHistoryRepository;
import com.tcGroup.trainingCenter.utility.logic.AbstractDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("trainingHistoryDAO")
public class TrainingHistoryDAO extends AbstractDAO<TrainingHistoryData, Long> {


    @Autowired
    protected void setRepository(TrainingHistoryRepository repository) {
        super.setRepository(repository);
    }

    protected TrainingHistoryRepository getRepository() {
        return (TrainingHistoryRepository) super.getRepository();
    }

    public List<TrainingHistoryData> findByAccountId(Long id) {
        return this.getRepository().findAllByAccount_Id(id);
    }

}
