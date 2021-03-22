package com.tcGroup.trainingCenter.role;

import com.tcGroup.trainingCenter.utility.logic.AbstractDAO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component(value = "roleDAO")
public class RoleDAO extends AbstractDAO<Role, Long> {

    public List<Role> findAll() {
        return this.getRepository().findAll();
    }
    public Role save(Role account) {
        return this.getRepository().save(account);
    }
    public void deleteById(long id) {
        this.getRepository().deleteById(id);
    }
    public Optional<Role> findById(long id) {
        return this.getRepository().findById(id);
    }
}
