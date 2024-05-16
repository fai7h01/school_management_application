package com.cydeo.service.impl;

import com.cydeo.entity.Role;
import com.cydeo.service.RoleService;

import java.util.List;

public class RoleServiceImpl extends AbstractMapService<Role, Long> implements RoleService {
    @Override
    public Role save(Role object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Role findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return super.findAll();
    }

    @Override
    public void update(Role object) {
        super.update(object.getId(), object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
