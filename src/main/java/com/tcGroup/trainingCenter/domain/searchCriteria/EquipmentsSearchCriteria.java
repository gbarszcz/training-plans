package com.tcGroup.trainingCenter.domain.searchCriteria;

import java.util.List;

import com.tcGroup.trainingCenter.domain.entity.EquipmentData;
import com.tcGroup.trainingCenter.utility.searchCriteria.SearchCriteria;
import com.tcGroup.trainingCenter.utility.searchCriteria.annotation.SearchField;
import com.tcGroup.trainingCenter.utility.searchCriteria.enumeration.SearchOperation;

import lombok.Data;

@Data
public class EquipmentsSearchCriteria extends SearchCriteria<EquipmentData> {

    @SearchField(field = "equipmentName", operation = SearchOperation.MATCH)
    private String equipmentName;

    @SearchField(field = "equipmentName", operation = SearchOperation.IN)
    private List<String> equipmentNameIn;
    
}