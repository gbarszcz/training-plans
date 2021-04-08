package com.tcGroup.trainingCenter.user.service;

import com.tcGroup.trainingCenter.user.entity.AccountData;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountManagementService extends UserDetailsService {

    // -------------------------- ACCOUNTS -------------------------------------

    AccountData getAccountByEmail(String email);
}
