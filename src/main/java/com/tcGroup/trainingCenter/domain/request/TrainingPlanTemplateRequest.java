package com.tcGroup.trainingCenter.domain.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
public class TrainingPlanTemplateRequest {

    private final String name;

    @NotNull
    private final long accountId;

    private List<TrainingSeriesTemplateDTO> seriesTemplates;

    public TrainingPlanTemplateRequest(String name,
                                       long accountId,
                                       List<TrainingSeriesTemplateDTO> seriesTemplates) {
        this.name = name;
        this.accountId = accountId;
        this.seriesTemplates = seriesTemplates;
    }
}
