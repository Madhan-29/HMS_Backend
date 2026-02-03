package com.springboot.HMS.ServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.HMS.Entity.Role;
import com.springboot.HMS.Repository.RoleRepository;
import com.springboot.HMS.Service.RoleService;

@Service
public class RoleServiceImplementation implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name)
            .orElseThrow(() ->
                new RuntimeException("Role not found: " + name)
            );
    }
}
