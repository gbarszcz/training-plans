package com.tcGroup.trainingCenter.domain.controller;

import com.tcGroup.trainingCenter.domain.entity.TrainingHistoryData;
import com.tcGroup.trainingCenter.domain.request.TrainingHistoryRequest;
import com.tcGroup.trainingCenter.domain.service.TrainingHistoryService;
import com.tcGroup.trainingCenter.utility.logic.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("training/history")
@RestController
public class TrainingHistoryController extends AbstractController {

    @Autowired
    private TrainingHistoryService trainingHistoryService;

    @GetMapping("/")
    public List<TrainingHistoryData> getTrainingHistoryData() {
        return trainingHistoryService.getAllDataForAccount();
    }

    @GetMapping("/{id}")
    public TrainingHistoryData getTrainingData(@PathVariable Long id) {
        return trainingHistoryService.getTrainingPlan(id);
    }

    @PostMapping("/add")
    public Long addTrainingData(@RequestBody @Valid TrainingHistoryRequest request) {
        return trainingHistoryService.createTrainingPlan(request);
    }

    @PutMapping("/modify")
    public TrainingHistoryData modifyTrainingData(@RequestBody @Valid TrainingHistoryRequest request) {
        return trainingHistoryService.modifyTrainingPlan(request);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteTrainingData(@PathVariable Long id) {
        return trainingHistoryService.deleteTrainingPlan(id);
    }

}
