package com.tcGroup.trainingCenter.domain.repository;

import com.tcGroup.trainingCenter.domain.entity.TrainingSeriesTemplateData;
import com.tcGroup.trainingCenter.utility.logic.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingSeriesTemplateRepository extends AbstractRepository<TrainingSeriesTemplateData, Long> {

}
