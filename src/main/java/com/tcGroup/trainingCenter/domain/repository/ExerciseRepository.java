package com.tcGroup.trainingCenter.domain.repository;

import com.tcGroup.trainingCenter.domain.entity.ExerciseData;
import com.tcGroup.trainingCenter.utility.logic.AbstractRepository;

import org.springframework.stereotype.Repository;

@Repository("exerciseRepo")
public interface ExerciseRepository extends AbstractRepository<ExerciseData, Long> {
    
}