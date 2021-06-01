package com.tcGroup.trainingCenter.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class ExerciseStatsResponse {
    private Date date;
    private Double iterationCount;
    private Double result;
}
