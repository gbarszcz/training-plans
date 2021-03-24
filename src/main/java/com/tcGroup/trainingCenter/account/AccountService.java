package com.tcGroup.trainingCenter.account;

import com.tcGroup.trainingCenter.registration.RegistrationRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service("accountService")
public interface AccountService extends UserDetailsService {

    Long register(RegistrationRequest registrationRequest);

    Account getAccountByEmail(String email);
}
