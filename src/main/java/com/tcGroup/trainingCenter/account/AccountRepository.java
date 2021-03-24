package com.tcGroup.trainingCenter.account;

import com.tcGroup.trainingCenter.utility.logic.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends AbstractRepository<Account, Long> {

    Account findByAccountEmail(String accountEmail);

}