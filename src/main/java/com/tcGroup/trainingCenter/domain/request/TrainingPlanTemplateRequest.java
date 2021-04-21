package com.tcGroup.trainingCenter.domain.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class TrainingPlanTemplateRequest {

    private final String name;
    @NotNull
    //TODO S-003B - account id should be taken from user context
    private final long accountId;
    private final List<TrainingSeriesTemplateDTO> seriesTemplates;

}
