package com.tcGroup.trainingCenter.account;

import com.tcGroup.trainingCenter.registration.RegistrationRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {

    Long register(RegistrationRequest registrationRequest);

    Account getAccountByEmail(String email);
}
