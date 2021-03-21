package com.tcGroup.trainingCenter.role;

import com.tcGroup.trainingCenter.utility.entity.AuditData;
import com.tcGroup.trainingCenter.utility.logic.AbstractDAO;
import com.tcGroup.trainingCenter.utility.logic.AbstractRepository;
import org.springframework.stereotype.Component;

@Component(value = "roleDAO")
public class RoleDAO extends AbstractDAO {

    @Override
    protected void setRepository(AbstractRepository<AuditData, Long> repository) {
        super.setRepository(repository);
    }
}
