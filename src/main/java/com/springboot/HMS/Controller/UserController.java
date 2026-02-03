package com.springboot.HMS.Controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.springboot.HMS.Entity.UserEntity;
import com.springboot.HMS.Service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/me")
    public UserEntity getMyProfile(Authentication auth) {
        return userService.getMyProfile(auth.getName());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/status")
    public String updateUserStatus(@PathVariable Long id, @RequestParam boolean enabled) {
        return userService.updateUserStatus(id, enabled);
    }
}
