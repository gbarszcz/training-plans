package com.tcGroup.trainingCenter.account;

import com.tcGroup.trainingCenter.utility.logic.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AccountServiceImpl extends AbstractService implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> listAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void deleteById(long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Optional<Account> findById(long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account findByEmailAddress(String emailAddress) {
        return accountRepository.findByAccountEmail(emailAddress);
    }

}
