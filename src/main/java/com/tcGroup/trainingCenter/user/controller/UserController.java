package com.tcGroup.trainingCenter.user.controller;

import com.tcGroup.trainingCenter.user.entity.AccountData;
import com.tcGroup.trainingCenter.user.request.UpdateProfileRequest;
import com.tcGroup.trainingCenter.user.service.AccountManagementService;
import com.tcGroup.trainingCenter.utility.context.UserContext;
import com.tcGroup.trainingCenter.utility.logic.AbstractController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends AbstractController {

    @Autowired
    AccountManagementService accountManagementService;

    @GetMapping(value = { "/login", "/user"})
    public UserContext getCurrentUser() {
        return this.getUserContext();
    }

    @GetMapping("/profile")
    public AccountData getCurrentUserProfile() {
        Long accountId = getUserContext().getUserId();

        return accountManagementService.getAccountById(accountId);
    }

    @PutMapping("/profile")
    public Long updateCurrentUserProfile(@RequestBody UpdateProfileRequest updateProfileRequest) {
        AccountData accountData = accountManagementService.getAccountById(getUserContext().getUserId());
        
        accountData.setFirstName(updateProfileRequest.getFirstName());
        accountData.setLastName(updateProfileRequest.getLastName());
        accountData.setIdentifier(updateProfileRequest.getIdentifier());
        accountData.setBirthdate(updateProfileRequest.getBirthdate());
        accountData.setDescription(updateProfileRequest.getDescription());

        return accountManagementService.modifyAccount(accountData);
    }

}
