package com.tcGroup.trainingCenter.role;

import com.tcGroup.trainingCenter.utility.logic.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends AbstractRepository<Role, Long> {

    Role findByRoleName(String roleName);

}
