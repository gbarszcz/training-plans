package com.tcGroup.trainingCenter.user.enumeration.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.tcGroup.trainingCenter.user.enumeration.AccountStatus;

@Converter(autoApply = true)
public class AccountStatusConverter implements AttributeConverter<AccountStatus, String>{

    @Override
    public String convertToDatabaseColumn(AccountStatus status) {
        return (status != null) ? status.getAbbreviation() : null;
    }

    @Override
    public AccountStatus convertToEntityAttribute(String dbData) {
        return (dbData != null) ? AccountStatus.fromAbbreviation(dbData) : null;
    }
    
}