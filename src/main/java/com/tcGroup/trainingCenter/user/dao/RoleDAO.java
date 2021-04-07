package com.tcGroup.trainingCenter.user.dao;

import com.tcGroup.trainingCenter.user.entity.RoleData;
import com.tcGroup.trainingCenter.user.enumeration.RoleName;
import com.tcGroup.trainingCenter.user.repository.RoleRepository;
import com.tcGroup.trainingCenter.utility.logic.AbstractDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "roleDAO")
public class RoleDAO extends AbstractDAO<RoleData, Long> {

    public RoleData findRoleByName(RoleName roleName) {
        return this.getRepository().findByRoleName(roleName);
    }

    @Autowired
    protected void setRepository(RoleRepository roleRepository) {
        super.setRepository(roleRepository);
    }

    protected RoleRepository getRepository() {
        return (RoleRepository) super.getRepository();
    }

}
