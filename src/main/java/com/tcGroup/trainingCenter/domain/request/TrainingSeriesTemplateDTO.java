package com.tcGroup.trainingCenter.domain.request;

import lombok.Getter;

@Getter
public class TrainingSeriesTemplateDTO {

    private final long exerciseId;
    private final int trainingUnit;

    public TrainingSeriesTemplateDTO(long exerciseId, int trainingUnit) {
        this.exerciseId = exerciseId;
        this.trainingUnit = trainingUnit;
    }
}