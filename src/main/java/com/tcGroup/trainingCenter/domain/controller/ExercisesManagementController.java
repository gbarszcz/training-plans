package com.tcGroup.trainingCenter.domain.controller;

import java.util.List;

import com.tcGroup.trainingCenter.domain.entity.ExerciseData;
import com.tcGroup.trainingCenter.domain.entity.TagData;
import com.tcGroup.trainingCenter.domain.service.ExercisesManagementService;
import com.tcGroup.trainingCenter.utility.logic.AbstractController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("exercisesManagementController")
public class ExercisesManagementController extends AbstractController {
    
    @Autowired
    private ExercisesManagementService exercisesManagementService;

    //--------------------- EXERCISES ----------------------------------

    @GetMapping(path = "/exercises")
    public List<ExerciseData> getExercises() {
        return exercisesManagementService.getAllExercises();
    }

    @GetMapping(path = "/exercise/{id}")
    public ExerciseData getExercise(@PathVariable Long id) {
        return exercisesManagementService.getExercise(id);
    }

    // ----------------------- TAGS -------------------------------------

    @GetMapping(path = "/tags")
    public List<TagData> getTags() {
        return exercisesManagementService.getAllTags();
    }
}