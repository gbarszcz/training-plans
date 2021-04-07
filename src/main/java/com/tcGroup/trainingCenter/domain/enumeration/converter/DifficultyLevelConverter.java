package com.tcGroup.trainingCenter.domain.enumeration.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.tcGroup.trainingCenter.domain.enumeration.DifficultyLevel;

@Converter(autoApply = true)
public class DifficultyLevelConverter implements AttributeConverter<DifficultyLevel, String>{

    @Override
    public String convertToDatabaseColumn(DifficultyLevel level) {
        return (level != null) ? level.getAbbreviation() : null;
    }

    @Override
    public DifficultyLevel convertToEntityAttribute(String dbData) {
        return (dbData != null) ? DifficultyLevel.fromAbbreviation(dbData) : null;
    }
    
}