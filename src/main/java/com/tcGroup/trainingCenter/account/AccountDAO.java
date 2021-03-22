package com.tcGroup.trainingCenter.account;

import com.tcGroup.trainingCenter.utility.logic.AbstractDAO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component(value = "accountDAO")
public class AccountDAO extends AbstractDAO<Account, Long> {

    public List<Account> findAll() {
        return this.getRepository().findAll();
    }

    public Account save(Account account) {
        return this.getRepository().save(account);
    }

    public void deleteById(long id) {
        this.getRepository().deleteById(id);
    }

    public Optional<Account> findById(long id) {
        return this.getRepository().findById(id);
    }

}
