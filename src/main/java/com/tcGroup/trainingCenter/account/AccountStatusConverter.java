package com.tcGroup.trainingCenter.account;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AccountStatusConverter implements AttributeConverter<AccountStatus, String> {
    @Override
    public String convertToDatabaseColumn(AccountStatus attribute) {
        if (attribute != null)
            return attribute.getAbbreviation();
        else
            return null;
    }

    @Override
    public AccountStatus convertToEntityAttribute(String dbData) {
        return AccountStatus.valueOf(dbData);
    }
}
