package com.tcGroup.trainingCenter.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String email;
    private final String password;

    public RegistrationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}


