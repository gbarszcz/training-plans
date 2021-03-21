package com.tcGroup.trainingCenter.role;

import com.tcGroup.trainingCenter.utility.logic.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class RoleServiceImpl extends AbstractService implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> listAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role save(Role account) {
        return roleRepository.save(account);
    }

    @Override
    public void deleteById(long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Optional<Role> findById(long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role findByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

}
