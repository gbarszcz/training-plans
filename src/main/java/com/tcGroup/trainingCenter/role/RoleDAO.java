package com.tcGroup.trainingCenter.role;

import com.tcGroup.trainingCenter.utility.logic.AbstractDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "roleDAO")
public class RoleDAO extends AbstractDAO<Role, Long> {

    @Autowired
    protected void setRepository(RoleRepository roleRepository) {
        super.setRepository(roleRepository);
    }

}
