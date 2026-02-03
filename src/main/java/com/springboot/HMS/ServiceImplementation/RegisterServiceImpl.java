package com.springboot.HMS.ServiceImplementation;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.HMS.Entity.Role;
import com.springboot.HMS.Entity.UserEntity;
import com.springboot.HMS.Repository.UserRepository;
import com.springboot.HMS.Service.RegisterService;
import com.springboot.HMS.Service.RoleService;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public RegisterServiceImpl(UserRepository userRepository,
                               PasswordEncoder passwordEncoder,
                               RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public String register(UserEntity user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email already exists";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);

        Role role = roleService.getRoleByName("ROLE_USER");
        user.getRoles().add(role);

        userRepository.save(user);

        return "User registered successfully";
    }
}
