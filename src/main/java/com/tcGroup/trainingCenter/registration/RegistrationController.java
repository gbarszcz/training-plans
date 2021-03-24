package com.tcGroup.trainingCenter.registration;

import com.tcGroup.trainingCenter.account.AccountService;
import com.tcGroup.trainingCenter.utility.logic.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController extends AbstractController {

    @Autowired
    public AccountService accountService;



    @PostMapping("/register")
    public String register(@RequestBody RegistrationRequest registrationRequest){

        accountService.register();

        //accountService.save()
        return "succesfull";
    }


}
