package com.tcGroup.trainingCenter.utility;

import java.text.MessageFormat;

public class ApplicationException extends Exception {

    public ApplicationException(String errorMessage, Object... msgParams) {
        super(new MessageFormat(errorMessage).format(msgParams));
    }

}
