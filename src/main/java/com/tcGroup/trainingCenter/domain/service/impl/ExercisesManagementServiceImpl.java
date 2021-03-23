package com.tcGroup.trainingCenter.domain.service.impl;

import java.util.List;

import com.tcGroup.trainingCenter.domain.dao.ExerciseDAO;
import com.tcGroup.trainingCenter.domain.dao.TagDAO;
import com.tcGroup.trainingCenter.domain.entity.ExerciseData;
import com.tcGroup.trainingCenter.domain.entity.TagData;
import com.tcGroup.trainingCenter.domain.service.ExercisesManagementService;
import com.tcGroup.trainingCenter.utility.logic.AbstractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "exercisesManagementService")
public class ExercisesManagementServiceImpl extends AbstractService implements ExercisesManagementService {

    @Autowired
    private ExerciseDAO exerciseDAO;

    @Autowired
    private TagDAO tagDAO;

    //--------------------- EXERCISES ----------------------------------

    @Override
    @Transactional
    public List<ExerciseData> getAllExercises() {
        return exerciseDAO.getItems();
    }

    @Override
    @Transactional
    public ExerciseData getExercise(Long id) {
        return exerciseDAO.getItem(id);
    }

    @Override
    @Transactional
    public Long createExercise(ExerciseData exercise) {
        return exerciseDAO.createItem(getUserContext(), exercise);
    }

    @Override
    @Transactional
    public Long modifyExercise(ExerciseData exercise) {
        return exerciseDAO.modifyItem(getUserContext(), exercise);
    }

    @Override
    @Transactional
    public void removeExercise(Long id) {
        ExerciseData exercise = exerciseDAO.getItem(id);
        if (exercise == null) {
            // TODO throw exception
        } else {
            exerciseDAO.removeItem(getUserContext(), exercise);
        }
    }

    // ----------------------- TAGS -------------------------------------

    @Override
    @Transactional
    public List<TagData> getAllTags() {
        return tagDAO.getItems();
    }

    @Override
    @Transactional
    public TagData getTag(Long id) {
        return tagDAO.getItem(id);
    }

    @Override
    @Transactional
    public Long createTag(TagData tag) {
        return tagDAO.createItem(getUserContext(), tag);
    }

    @Override
    @Transactional
    public Long modifyTag(TagData tag) {
        return tagDAO.modifyItem(getUserContext(), tag);
    }

    @Override
    @Transactional
    public void removeTag(Long id) {
        TagData tag = tagDAO.getItem(id);
        if (tag == null) {
            // TODO throw exception
        } else {
            tagDAO.removeItem(getUserContext(), tag);
        }
    }
    
}