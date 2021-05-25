package com.tcGroup.trainingCenter.domain.searchCriteria;

import com.tcGroup.trainingCenter.domain.entity.TrainingPlanTemplateData;
import com.tcGroup.trainingCenter.utility.searchCriteria.SearchCriteria;
import com.tcGroup.trainingCenter.utility.searchCriteria.annotation.SearchField;
import com.tcGroup.trainingCenter.utility.searchCriteria.enumeration.SearchOperation;

import lombok.Data;

@Data
public class TrainingPlanTemplatesSearchCriteria extends SearchCriteria<TrainingPlanTemplateData>{
    
    @SearchField(field = "name", operation = SearchOperation.MATCH)
    private String name;
}