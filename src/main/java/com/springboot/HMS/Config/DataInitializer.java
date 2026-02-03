package com.springboot.HMS.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.HMS.Entity.Role;
import com.springboot.HMS.Repository.RoleRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {

            if (roleRepository.findByName("ROLE_USER").isEmpty()) {
                Role user = new Role();
                user.setName("ROLE_USER");
                user.setDescription("Default user role");
                roleRepository.save(user);
            }

            if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
                Role admin = new Role();
                admin.setName("ROLE_ADMIN");
                admin.setDescription("System admin");
                roleRepository.save(admin);
            }
        };
    }
}
