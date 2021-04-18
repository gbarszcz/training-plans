package com.tcGroup.trainingCenter.domain.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class TrainingSeriesDataDTO {

    @NotNull
    private final long trainingSeriesId;
    @NotNull
    private final long exerciseId;
    private final int trainingUnit;
    private final TrainingSeriesResultDataDTO trainingSeriesResultData;
}
