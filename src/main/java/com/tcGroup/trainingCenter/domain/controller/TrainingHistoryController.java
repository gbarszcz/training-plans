package com.tcGroup.trainingCenter.domain.controller;

import com.tcGroup.trainingCenter.domain.entity.TrainingHistoryData;
import com.tcGroup.trainingCenter.domain.request.TrainingHistoryRequest;
import com.tcGroup.trainingCenter.domain.service.TrainingHistoryService;
import com.tcGroup.trainingCenter.utility.ApplicationException;
import com.tcGroup.trainingCenter.utility.logic.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("training/history")
@RestController
public class TrainingHistoryController extends AbstractController {

    @Autowired
    private TrainingHistoryService trainingHistoryService;

    @GetMapping
    public List<TrainingHistoryData> getTrainingHistoryData() {
        return trainingHistoryService.getAllDataForAccount();
    }

    @GetMapping("/{id}")
    public TrainingHistoryData getTrainingData(@PathVariable Long id) {
        try {
            return trainingHistoryService.getTrainingPlan(id);
        } catch (ApplicationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PostMapping
    public Long addTrainingData(@RequestBody @Valid TrainingHistoryRequest request) {
        return trainingHistoryService.createTrainingPlan(request);
    }

    @PutMapping
    public TrainingHistoryData modifyTrainingData(@RequestBody @Valid TrainingHistoryRequest request) {
        try {
            return trainingHistoryService.modifyTrainingPlan(request);
        } catch (ApplicationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public boolean deleteTrainingData(@PathVariable Long id) {
        try {
            return trainingHistoryService.deleteTrainingPlan(id);
        } catch (ApplicationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

}
