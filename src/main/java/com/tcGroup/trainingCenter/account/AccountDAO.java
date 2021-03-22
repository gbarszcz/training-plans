package com.tcGroup.trainingCenter.account;

import com.tcGroup.trainingCenter.utility.logic.AbstractDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "accountDAO")
public class AccountDAO extends AbstractDAO<Account, Long> {

    @Autowired
    protected void setRepository(AccountRepository accountRepository) {
        super.setRepository(accountRepository);
    }

}
