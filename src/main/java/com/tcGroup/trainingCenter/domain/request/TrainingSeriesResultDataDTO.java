package com.tcGroup.trainingCenter.domain.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class TrainingSeriesResultDataDTO {

    private final long resultId;
    private final String iterationUnit;
    private final Double iterationCount;
    private final Double additionalWeight;
    private final Double result;

}
