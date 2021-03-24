package com.tcGroup.trainingCenter.account;

import com.tcGroup.trainingCenter.utility.context.UserContext;
import com.tcGroup.trainingCenter.utility.logic.AbstractDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "accountDAO")
public class AccountDAO extends AbstractDAO<Account, Long> {

    @Autowired
    protected void setRepository(AccountRepository accountRepository) {
        super.setRepository(accountRepository);
    }

    protected AccountRepository getRepository() {
        return this.getRepository();
    }

    @Override
    public void removeItem(UserContext ctx, Account accountData) {
        super.removeItem(ctx, accountData);
        Account foundAccount = getRepository().findByAccountEmail(accountData.getAccountEmail());
        foundAccount.setAccountStatus(AccountStatus.INACTIVE);
        getRepository().save(foundAccount);
    }
}
