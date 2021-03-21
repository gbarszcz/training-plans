package com.tcGroup.trainingCenter.account;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("accountService")
public interface AccountService {

    List<Account> listAll();
    Account save(Account account);
    void deleteById(long id);
    Optional<Account> findById(long id);
    Account findByEmailAddress(String emailAddress);

}
