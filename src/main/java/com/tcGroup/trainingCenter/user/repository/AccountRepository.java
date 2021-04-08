package com.tcGroup.trainingCenter.user.repository;

import com.tcGroup.trainingCenter.user.entity.AccountData;
import com.tcGroup.trainingCenter.utility.logic.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends AbstractRepository<AccountData, Long> {

    AccountData findByAccountEmail(String accountEmail);

}