package com.tcGroup.trainingCenter.domain.repository;

import com.tcGroup.trainingCenter.domain.entity.TrainingPlanTemplateData;
import com.tcGroup.trainingCenter.utility.logic.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("trainingPlanTemplateRepository")
public interface TrainingPlanTemplateRepository extends AbstractRepository<TrainingPlanTemplateData, Long> {

    TrainingPlanTemplateData findByName(String name);
    List<TrainingPlanTemplateData> findAllByAccount_Id(long accountId);

}
