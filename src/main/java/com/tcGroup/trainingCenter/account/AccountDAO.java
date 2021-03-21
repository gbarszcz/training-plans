package com.tcGroup.trainingCenter.account;

import com.tcGroup.trainingCenter.utility.entity.AuditData;
import com.tcGroup.trainingCenter.utility.logic.AbstractDAO;
import com.tcGroup.trainingCenter.utility.logic.AbstractRepository;
import org.springframework.stereotype.Component;

@Component(value = "accountDAO")
public class AccountDAO extends AbstractDAO {

    @Override
    protected void setRepository(AbstractRepository<AuditData, Long> repository) {
        super.setRepository(repository);
    }

}
