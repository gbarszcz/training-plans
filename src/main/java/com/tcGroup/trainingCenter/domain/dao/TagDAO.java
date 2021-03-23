package com.tcGroup.trainingCenter.domain.dao;

import com.tcGroup.trainingCenter.domain.entity.TagData;
import com.tcGroup.trainingCenter.domain.repository.TagRepository;
import com.tcGroup.trainingCenter.utility.logic.AbstractDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("tagDAO")
public class TagDAO extends AbstractDAO<TagData, Long>{
    
    @Autowired
    protected void setRepository(TagRepository repository) {
        super.setRepository(repository);
    }
}