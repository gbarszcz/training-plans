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

    @GetMapping("/all/{accountId}")
    public List<TrainingHistoryData> getTrainingHistoryData(@PathVariable Long accountId) {
        return trainingHistoryService.getAllDataForAccount(accountId);
    }

    @PostMapping("/add")
    public Long addTrainingData(@RequestBody @Valid TrainingHistoryRequest request) {
        return trainingHistoryService.createTrainingPlan(request);
    }

    @PostMapping("/modify")
    public TrainingHistoryData modifyTrainingData(@RequestBody @Valid TrainingHistoryRequest request) {
        return trainingHistoryService.modifyTrainingPlan(request);
    }

}
