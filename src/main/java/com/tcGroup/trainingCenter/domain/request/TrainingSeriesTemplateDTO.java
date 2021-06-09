package com.tcGroup.trainingCenter.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TrainingSeriesTemplateDTO {

    private final Long id;
    private final long exerciseId;
    private final int trainingUnit;

}