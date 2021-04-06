package com.tcGroup.trainingCenter.user.controller;

import java.util.regex.Pattern;

import com.tcGroup.trainingCenter.user.entity.AccountData;
import com.tcGroup.trainingCenter.user.request.RegistrationRequest;
import com.tcGroup.trainingCenter.user.service.AccountManagementService;
import com.tcGroup.trainingCenter.user.service.RegistrationService;
import com.tcGroup.trainingCenter.utility.AppParams;
import com.tcGroup.trainingCenter.utility.logic.AbstractController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RegistrationController extends AbstractController {

    @Autowired
    public AccountManagementService accountService;

    @Autowired
    public RegistrationService registrationService;

    @PostMapping("/register")
    public Long register(@RequestBody RegistrationRequest registrationRequest) {
        Pattern patternEmail = Pattern.compile(AppParams.EMAIL_REGEX);
        Pattern patternPassword = Pattern.compile(AppParams.PASSWORD_REGEX);

        String email = registrationRequest.getEmail();
        String password = registrationRequest.getPassword();

        AccountData existingAccount = accountService.getAccountByEmail(email);

        if (existingAccount != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account with given email already exist");
        }

        if (email == null || email.isBlank() || !patternEmail.matcher(email).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email invalid");
        }

        if (password == null || password.isBlank() || !patternPassword.matcher(password).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password invalid");
        }

        Long accountID = registrationService.register(registrationRequest);

        return accountID;
    }

}
