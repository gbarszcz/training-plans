package com.tcGroup.trainingCenter.domain.repository;

import com.tcGroup.trainingCenter.domain.entity.TagData;
import com.tcGroup.trainingCenter.utility.logic.AbstractRepository;

import org.springframework.stereotype.Repository;

@Repository("tagRepository")
public interface TagRepository extends AbstractRepository<TagData, Long>{
    
}