package com.tcGroup.trainingCenter.user.controller;

import com.tcGroup.trainingCenter.user.request.RegistrationRequest;
import com.tcGroup.trainingCenter.user.response.RegisterSuccessfulResponse;
import com.tcGroup.trainingCenter.user.service.AccountManagementService;
import com.tcGroup.trainingCenter.user.service.RegistrationService;
import com.tcGroup.trainingCenter.utility.logic.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class RegistrationController extends AbstractController {

    @Autowired
    public AccountManagementService accountService;

    @Autowired
    public RegistrationService registrationService;

    @PostMapping("/register")
    public RegisterSuccessfulResponse register(@RequestBody @Valid RegistrationRequest registrationRequest) {
        Long accountID = registrationService.register(registrationRequest);

        return new RegisterSuccessfulResponse(accountID);
    }

}
