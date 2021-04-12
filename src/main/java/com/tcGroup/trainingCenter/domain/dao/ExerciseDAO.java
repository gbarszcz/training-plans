package com.tcGroup.trainingCenter.domain.dao;

import com.tcGroup.trainingCenter.domain.entity.ExerciseData;
import com.tcGroup.trainingCenter.domain.repository.ExerciseRepository;
import com.tcGroup.trainingCenter.utility.logic.AbstractDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("exerciseDAO")
public class ExerciseDAO extends AbstractDAO<ExerciseData, Long> {
    
    @Autowired
    protected void setRepository(ExerciseRepository repository) {
        super.setRepository(repository);
    }

    protected ExerciseRepository getRepository() {
        return (ExerciseRepository) super.getRepository();
    }

    public List<ExerciseData> findExerciseByTag(Long id) {
        return this.getRepository().findByTags_Id(id);
    }
}