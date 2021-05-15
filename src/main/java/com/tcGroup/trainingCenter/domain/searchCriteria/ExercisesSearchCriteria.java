package com.tcGroup.trainingCenter.domain.searchCriteria;

import java.util.List;

import com.tcGroup.trainingCenter.domain.entity.ExerciseData;
import com.tcGroup.trainingCenter.domain.enumeration.DifficultyLevel;
import com.tcGroup.trainingCenter.utility.searchCriteria.SearchCriteria;
import com.tcGroup.trainingCenter.utility.searchCriteria.annotation.SearchField;
import com.tcGroup.trainingCenter.utility.searchCriteria.enumeration.SearchOperation;

import lombok.Data;

@Data
public class ExercisesSearchCriteria extends SearchCriteria<ExerciseData> {

    @SearchField(field = "exerciseName", operation = SearchOperation.MATCH)
    private String exerciseName;

    @SearchField(field = "exerciseName", operation = SearchOperation.IN)
    private List<String> exerciseNameIn;

    @SearchField(field = "exerciseDifficultyLvl", operation = SearchOperation.EQUAL)
    private DifficultyLevel exerciseDifficultyLevel;

    @SearchField(field = "exerciseDemo", operation = SearchOperation.EQUAL)
    private Boolean exerciseDemo;
}