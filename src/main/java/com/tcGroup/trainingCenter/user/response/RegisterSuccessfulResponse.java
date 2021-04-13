package com.tcGroup.trainingCenter.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class RegisterSuccessfulResponse {
    
    private Long accountId;
}