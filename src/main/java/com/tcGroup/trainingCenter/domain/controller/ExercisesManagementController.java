package com.tcGroup.trainingCenter.domain.controller;

import java.util.List;

import com.tcGroup.trainingCenter.domain.entity.ExerciseData;
import com.tcGroup.trainingCenter.domain.entity.TagData;
import com.tcGroup.trainingCenter.domain.searchCriteria.ExercisesSearchCriteria;
import com.tcGroup.trainingCenter.domain.searchCriteria.TagsSearchCriteria;
import com.tcGroup.trainingCenter.domain.service.ExercisesManagementService;
import com.tcGroup.trainingCenter.utility.logic.AbstractController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("exercisesManagementController")
public class ExercisesManagementController extends AbstractController {
    
    @Autowired
    private ExercisesManagementService exercisesManagementService;

    //--------------------- EXERCISES ----------------------------------

    @GetMapping(path = "/exercises")
    public List<ExerciseData> getExercises() {
        return exercisesManagementService.getAllExercises(new ExercisesSearchCriteria());
    }

    @PostMapping(path = "/exercises")
    public List<ExerciseData> getExercises(@RequestBody ExercisesSearchCriteria searchCriteria) {
        return exercisesManagementService.getAllExercises(searchCriteria);
    }

    @GetMapping(path = "/exercise/{id}")
    public ExerciseData getExercise(@PathVariable Long id) {
        return exercisesManagementService.getExercise(id);
    }

    // ----------------------- TAGS -------------------------------------

    @GetMapping(path = "/tags")
    public List<TagData> getTags(@RequestBody TagsSearchCriteria searchCriteria) {
        return exercisesManagementService.getAllTags(searchCriteria);
    }

    @GetMapping(path = "/exercises/tag/{id}")
    public List<ExerciseData> getExercisesByTag(@PathVariable Long id) {
        return exercisesManagementService.getExercisesByTag(id);
    }
}