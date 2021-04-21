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
    //TODO S-003B - account id should be taken from user context
    private final long accountId;
    private final long templateId;
    @NotNull
    private final LocalDate trainingDate;
    private final List<TrainingSeriesDataDTO> trainingSeriesData;

}
