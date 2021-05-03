package com.tcGroup.trainingCenter.user.response;

import com.tcGroup.trainingCenter.domain.entity.TrainingHistoryData;
import com.tcGroup.trainingCenter.domain.entity.TrainingPlanTemplateData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class AccountTrainingsPlansResponse {

    private List<TrainingHistoryData> trainings;
    private List<TrainingPlanTemplateData> trainingTemplates;

}
