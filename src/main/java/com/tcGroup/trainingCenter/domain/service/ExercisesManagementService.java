package com.tcGroup.trainingCenter.domain.service;

import java.util.List;

import com.tcGroup.trainingCenter.domain.entity.ExerciseData;
import com.tcGroup.trainingCenter.domain.entity.TagData;
import com.tcGroup.trainingCenter.domain.searchCriteria.ExercisesSearchCriteria;
import com.tcGroup.trainingCenter.domain.searchCriteria.TagsSearchCriteria;

public interface ExercisesManagementService {

    //--------------------- EXERCISES ----------------------------------
    
    List<ExerciseData> getAllExercises();

    List<ExerciseData> getAllExercises(ExercisesSearchCriteria searchCriteria);

    ExerciseData getExercise(Long id);

    Long createExercise(ExerciseData exercise);

    Long modifyExercise(ExerciseData exercise);

    void removeExercise(Long id);

    // ----------------------- TAGS -------------------------------------

    List<TagData> getAllTags();

    List<TagData> getAllTags(TagsSearchCriteria searchCriteria);

    TagData getTag(Long id);

    Long createTag(TagData tag);

    Long modifyTag(TagData tag);

    void removeTag(Long id);

    List<ExerciseData> getExercisesByTag(Long id);
}