package com.tcGroup.trainingCenter.domain.service;

import java.util.List;

import com.tcGroup.trainingCenter.domain.entity.ExerciseData;
import com.tcGroup.trainingCenter.domain.entity.TagData;

public interface ExercisesManagementService {

    //--------------------- EXERCISES ----------------------------------
    
    public List<ExerciseData> getAllExercises();

    public ExerciseData getExercise(Long id);

    public Long createExercise(ExerciseData exercise);

    public Long modifyExercise(ExerciseData exercise);

    public void removeExercise(Long id);

    // ----------------------- TAGS -------------------------------------

    public List<TagData> getAllTags();

    public TagData getTag(Long id);

    public Long createTag(TagData tag);

    public Long modifyTag(TagData tag);

    public void removeTag(Long id);
}