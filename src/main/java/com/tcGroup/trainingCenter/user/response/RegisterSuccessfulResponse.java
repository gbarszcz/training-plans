package com.tcGroup.trainingCenter.user.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class RegisterSuccessfulResponse implements Serializable {
    
    private Long accountId;
}