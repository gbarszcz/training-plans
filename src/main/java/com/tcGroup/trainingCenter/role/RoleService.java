package com.tcGroup.trainingCenter.role;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("roleService")
public interface RoleService {

    List<Role> listAll();
    Role save(Role account);
    void deleteById(long id);
    Optional<Role> findById(long id);
    Role findByName(String roleName);

}
