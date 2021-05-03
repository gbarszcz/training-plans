package com.tcGroup.trainingCenter.domain.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class TrainingHistoryRequest {

    private final long id;
    @NotNull
    private final long templateId;
    @NotNull
    private final LocalDate trainingDate;
    private final String title;
    private final List<TrainingSeriesDataDTO> trainingSeriesData;

}
