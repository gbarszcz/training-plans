package com.tcGroup.trainingCenter.user.request;

import com.tcGroup.trainingCenter.utility.AppParams;
import com.tcGroup.trainingCenter.validation.email.UniqueEmail;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    @NotBlank
    @Email(regexp = AppParams.EMAIL_REGEX, message = "{registration.email.invalid}")
    @UniqueEmail
    private final String email;


    @NotBlank
    @Pattern(regexp = AppParams.PASSWORD_REGEX, message = "{registration.password.invalid}")
    private final String password;

    public RegistrationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
