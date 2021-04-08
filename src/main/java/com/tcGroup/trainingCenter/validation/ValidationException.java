package com.tcGroup.trainingCenter.validation;

import com.tcGroup.trainingCenter.utility.ApplicationException;

public class ValidationException extends ApplicationException {

    public ValidationException(String errorMessage, Object... msgParams) {
        super(errorMessage, msgParams);
    }

}
