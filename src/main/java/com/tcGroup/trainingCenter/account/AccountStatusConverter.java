package com.tcGroup.trainingCenter.account;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AccountStatusConverter implements AttributeConverter<AccountStatus, String> {
    @Override
    public String convertToDatabaseColumn(AccountStatus attribute) {
        return attribute.getAbbreviation();
    }

    @Override
    public AccountStatus convertToEntityAttribute(String dbData) {
        return AccountStatus.valueOf(dbData);
    }
}
