package com.tcGroup.trainingCenter.domain.repository;

import com.tcGroup.trainingCenter.domain.entity.TrainingPlanTemplate;
import com.tcGroup.trainingCenter.utility.logic.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingPlanTemplateRepository extends AbstractRepository<TrainingPlanTemplate, Long> {

    TrainingPlanTemplate findByName(String name);
    List<TrainingPlanTemplate> findAllByAccount_Id(long accountId);

}
