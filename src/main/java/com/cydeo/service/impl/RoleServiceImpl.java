package com.cydeo.service.impl;

import com.cydeo.entity.Role;
import com.cydeo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends AbstractMapService<Role, Long> implements RoleService {

    RoleService roleService;

    public RoleServiceImpl(RoleService roleService) {
        this.roleService = roleService;
    }

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
    public void deleteById(Long id) {
        super.deleteById(id);

    }

    @Override
    public void update(Role object) {
        super.update(object.getId(), object);

    }
}
