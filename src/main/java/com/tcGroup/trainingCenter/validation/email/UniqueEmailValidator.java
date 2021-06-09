package com.tcGroup.trainingCenter.validation.email;

import javax.validation.*;

import com.tcGroup.trainingCenter.user.service.AccountManagementService;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private AccountManagementService accountManagementService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return accountManagementService.getAccountByEmail(value) == null;
    }

}