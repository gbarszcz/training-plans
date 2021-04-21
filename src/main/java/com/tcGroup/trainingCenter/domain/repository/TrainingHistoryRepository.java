package com.tcGroup.trainingCenter.domain.repository;

import com.tcGroup.trainingCenter.domain.entity.TrainingHistoryData;
import com.tcGroup.trainingCenter.utility.logic.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("trainingHistoryRepository")
public interface TrainingHistoryRepository extends AbstractRepository<TrainingHistoryData, Long> {

    List<TrainingHistoryData> findAllByAccount_Id(long accountId);
}
