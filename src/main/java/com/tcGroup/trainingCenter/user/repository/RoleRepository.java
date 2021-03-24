package com.tcGroup.trainingCenter.user.repository;

import com.tcGroup.trainingCenter.user.entity.RoleData;
import com.tcGroup.trainingCenter.utility.logic.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends AbstractRepository<RoleData, Long> {

    RoleData findByRoleName(String roleName);

}
