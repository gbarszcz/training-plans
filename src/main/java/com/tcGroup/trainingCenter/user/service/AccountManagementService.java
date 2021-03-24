package com.tcGroup.trainingCenter.user.service;

import com.tcGroup.trainingCenter.user.entity.AccountData;
import com.tcGroup.trainingCenter.user.registration.RegistrationRequest;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountManagementService extends UserDetailsService {

    // -------------------------- ACCOUNTS -------------------------------------

    Long register(RegistrationRequest registrationRequest);

    AccountData getAccountByEmail(String email);
}
