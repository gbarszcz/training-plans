package com.tcGroup.trainingCenter.account;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service("accountService")
public interface AccountService extends UserDetailsService {

}
