package com.tcGroup.trainingCenter.domain.repository;

import com.tcGroup.trainingCenter.domain.entity.TrainingSeriesResultData;
import com.tcGroup.trainingCenter.utility.logic.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository("trainingSeriesResultRepository")
public interface TrainingSeriesResultRepository extends AbstractRepository<TrainingSeriesResultData, Long> {

}
