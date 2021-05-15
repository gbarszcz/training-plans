package com.tcGroup.trainingCenter.domain.searchCriteria;

import java.util.List;

import com.tcGroup.trainingCenter.domain.entity.TagData;
import com.tcGroup.trainingCenter.utility.searchCriteria.SearchCriteria;
import com.tcGroup.trainingCenter.utility.searchCriteria.annotation.SearchField;
import com.tcGroup.trainingCenter.utility.searchCriteria.enumeration.SearchOperation;

import lombok.Data;

@Data
public class TagsSearchCriteria extends SearchCriteria<TagData> {
    
    @SearchField(field = "tagCode", operation = SearchOperation.MATCH)
    private String tagCode;

    @SearchField(field = "tagCode", operation = SearchOperation.IN)
    private List<String> tagCodeIn;

    @SearchField(field = "tagName", operation = SearchOperation.MATCH)
    private String tagName;

    @SearchField(field = "tagName", operation = SearchOperation.IN)
    private List<String> tagNameIn;
}