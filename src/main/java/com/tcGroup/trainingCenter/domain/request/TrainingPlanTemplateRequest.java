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
    private final long accountId;

    private List<TrainingSeriesTemplateDTO> seriesTemplates;

}
