package com.tcGroup.trainingCenter.domain.service;

import java.util.List;

import com.tcGroup.trainingCenter.domain.entity.ExerciseData;
import com.tcGroup.trainingCenter.domain.entity.TagData;

public interface ExercisesManagementService {

    //--------------------- EXERCISES ----------------------------------
    
    List<ExerciseData> getAllExercises();

    ExerciseData getExercise(Long id);

    Long createExercise(ExerciseData exercise);

    Long modifyExercise(ExerciseData exercise);

    void removeExercise(Long id);

    // ----------------------- TAGS -------------------------------------

    List<TagData> getAllTags();

    TagData getTag(Long id);

    Long createTag(TagData tag);

    Long modifyTag(TagData tag);

    void removeTag(Long id);
}