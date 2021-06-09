package com.tcGroup.trainingCenter.domain.repository;

import com.tcGroup.trainingCenter.domain.entity.TrainingSeriesData;
import com.tcGroup.trainingCenter.utility.logic.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository("trainingSeriesRepository")
public interface TrainingSeriesRepository extends AbstractRepository<TrainingSeriesData, Long> {

}
