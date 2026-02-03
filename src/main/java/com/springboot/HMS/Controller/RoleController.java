package com.springboot.HMS.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.springboot.HMS.Entity.Role;
import com.springboot.HMS.Service.RoleManagementService;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleManagementService roleManagementService;

    public RoleController(RoleManagementService roleManagementService) {
        this.roleManagementService = roleManagementService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Role createRole(@RequestBody Role role) {
        return roleManagementService.createRole(role);
    }
}
