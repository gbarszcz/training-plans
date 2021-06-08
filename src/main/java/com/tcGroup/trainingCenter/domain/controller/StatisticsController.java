package com.tcGroup.trainingCenter.domain.controller;

import com.tcGroup.trainingCenter.domain.entity.ExerciseData;
import com.tcGroup.trainingCenter.domain.entity.TrainingHistoryData;
import com.tcGroup.trainingCenter.domain.entity.TrainingSeriesData;
import com.tcGroup.trainingCenter.domain.entity.TrainingSeriesResultData;
import com.tcGroup.trainingCenter.domain.response.AggregateResponse;
import com.tcGroup.trainingCenter.domain.response.ExerciseStatsResponse;
import com.tcGroup.trainingCenter.domain.response.StatisticResponse;
import com.tcGroup.trainingCenter.domain.service.ExercisesManagementService;
import com.tcGroup.trainingCenter.domain.service.TrainingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

@RequestMapping("statistic")
@RestController
public class StatisticsController {

    @Autowired
    private ExercisesManagementService exercisesManagementService;

    @Autowired
    private TrainingHistoryService trainingHistoryService;

    @GetMapping(value = {"/{exerciseId}", "/{exerciseId}/{period}/{duration}"})
    public StatisticResponse getStatisticData(@PathVariable Long exerciseId,
                                              @PathVariable(required = false) String period,
                                              @PathVariable(required = false) Integer duration) {
        ExerciseData exercise = exercisesManagementService.getExercise(exerciseId);
        List<TrainingHistoryData> trainings = getStatistics(period, duration);
        Collection<ExerciseStatsResponse> results = mapExerciseTrainingsToResults(trainings, exercise).values();
        AggregateResponse aggregateResponse = getAggregateResponse(results);
        return new StatisticResponse(exercise, results, aggregateResponse);
    }

    private List<TrainingHistoryData> getStatistics(String period, Integer duration) {
        if (period != null) {
            ChronoUnit chronoUnit = ChronoUnit.valueOf(period.toUpperCase());
            LocalDate startDate = LocalDate.now().minus(duration, chronoUnit);
            Date from = Date.from(startDate.atStartOfDay()
                    .atZone(ZoneId.systemDefault())
                    .toInstant());
            Date to = Date.from(Instant.now());
            return trainingHistoryService.getStatisticsForPeriod(from, to);
        } else {
            return trainingHistoryService.getAllDataForAccount();
        }
    }

    private Map<Date, ExerciseStatsResponse> mapExerciseTrainingsToResults(List<TrainingHistoryData> trainings, ExerciseData exercise) {
        Map<Date, ExerciseStatsResponse> resultsPerDay = new HashMap<>();
        for (TrainingHistoryData training : trainings) {
            List<TrainingSeriesData> trainingSeries = training.getTrainingSeriesData()
                    .stream()
                    .filter(series -> series.getExercise() != null && series.getExercise().equals(exercise))
                    .collect(Collectors.toList());
            ExerciseStatsResponse exerciseStatsPerDay = resultsPerDay.get(training.getTrainingDate());
            if (exerciseStatsPerDay == null) {
                Date date = training.getTrainingDate();
                Double iterationCount = trainingSeries
                        .stream()
                        .map(TrainingSeriesData::getTrainingSeriesResultData)
                        .filter(el -> el.getIterationCount() != null)
                        .reduce(0d, (subtotal, element) -> subtotal + element.getIterationCount(), Double::sum);
                Double result = trainingSeries
                        .stream()
                        .map(TrainingSeriesData::getTrainingSeriesResultData)
                        .filter(el -> el.getResult() != null)
                        .reduce(0d, (subtotal, element) -> subtotal + element.getResult(), Double::sum);
                ExerciseStatsResponse exerciseStatsResponse = new ExerciseStatsResponse(date, iterationCount, result);
                resultsPerDay.put(training.getTrainingDate(), exerciseStatsResponse);
            } else {
                Double result = trainingSeries
                        .stream()
                        .map(TrainingSeriesData::getTrainingSeriesResultData)
                        .filter(el -> el.getResult() != null)
                        .reduce(exerciseStatsPerDay.getResult(), (subtotal, element) -> subtotal + element.getResult(), Double::sum);
                Double iterationCount = trainingSeries
                        .stream()
                        .map(TrainingSeriesData::getTrainingSeriesResultData)
                        .filter(el -> el.getResult() != null)
                        .reduce(exerciseStatsPerDay.getIterationCount(), (subtotal, element) -> subtotal + element.getIterationCount(), Double::sum);
                exerciseStatsPerDay.setResult(result);
                exerciseStatsPerDay.setIterationCount(iterationCount);
                resultsPerDay.put(training.getTrainingDate(), exerciseStatsPerDay);
            }
        }
        return resultsPerDay;
    }

    private AggregateResponse getAggregateResponse(Collection<ExerciseStatsResponse> exerciseStats) {
        return new AggregateResponse(
                exerciseStats.stream().mapToDouble(ExerciseStatsResponse::getIterationCount).max().orElse(0),
                exerciseStats.stream().mapToDouble(ExerciseStatsResponse::getIterationCount).min().orElse(0),
                exerciseStats.stream().mapToDouble(ExerciseStatsResponse::getIterationCount).average().orElse(0));
    }
}
