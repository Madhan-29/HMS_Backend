package com.springboot.HMS.ServiceImplementation;

import org.springframework.stereotype.Service;

import com.springboot.HMS.Entity.Role;
import com.springboot.HMS.Repository.RoleRepository;
import com.springboot.HMS.Service.RoleManagementService;

@Service
public class RoleManagementServiceImpl implements RoleManagementService {

    private final RoleRepository roleRepository;

    public RoleManagementServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }
}
