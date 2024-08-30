package com.cydeo.service.impl;

import com.cydeo.dto.RoleDTO;
import com.cydeo.entity.Role;
import com.cydeo.repository.RoleRepository;
import com.cydeo.service.RoleService;
import com.cydeo.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final MapperUtil mapper;

    public RoleServiceImpl(RoleRepository roleRepository, MapperUtil mapper) {
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @Override
    public List<RoleDTO> findAll() {
        return roleRepository.findAll().stream()
                .map(role -> mapper.convert(role, new RoleDTO()))
                .toList();
    }

    @Override
    public RoleDTO findById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found."));
        return mapper.convert(role, new RoleDTO());
    }
}
