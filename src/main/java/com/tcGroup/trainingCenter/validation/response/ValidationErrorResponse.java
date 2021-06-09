package com.tcGroup.trainingCenter.validation.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ValidationErrorResponse implements Serializable {
    
    private String field;

    private String message;
}