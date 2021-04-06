package com.tcGroup.trainingCenter.user.dao;

import com.tcGroup.trainingCenter.user.entity.AccountData;
import com.tcGroup.trainingCenter.user.enumeration.AccountStatus;
import com.tcGroup.trainingCenter.user.repository.AccountRepository;
import com.tcGroup.trainingCenter.utility.context.UserContext;
import com.tcGroup.trainingCenter.utility.logic.AbstractDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "accountDAO")
public class AccountDAO extends AbstractDAO<AccountData, Long> {

    @Autowired
    protected void setRepository(AccountRepository accountRepository) {
        super.setRepository(accountRepository);
    }

    protected AccountRepository getRepository() {
        return (AccountRepository) super.getRepository();
    }

    @Override
    public void removeItem(UserContext ctx, AccountData accountData) {
        super.removeItem(ctx, accountData);
        AccountData foundAccount = this.findAccountByEmail(accountData.getAccountEmail());
        foundAccount.setAccountStatus(AccountStatus.INACTIVE);
        getRepository().save(foundAccount);
    }

    public AccountData findAccountByEmail(String email){
        return getRepository().findByAccountEmail(email);
    }
}
