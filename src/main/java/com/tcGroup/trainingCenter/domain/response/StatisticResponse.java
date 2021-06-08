package com.tcGroup.trainingCenter.domain.response;

import com.tcGroup.trainingCenter.domain.entity.ExerciseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collection;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class StatisticResponse {
    private ExerciseData exercise;
    private Collection<ExerciseStatsResponse> exerciseStats;
    private AggregateResponse aggregates;
}
