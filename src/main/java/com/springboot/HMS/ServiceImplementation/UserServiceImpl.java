package com.springboot.HMS.ServiceImplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.HMS.Entity.UserEntity;
import com.springboot.HMS.Repository.UserRepository;
import com.springboot.HMS.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getMyProfile(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }

    @Override
    public String updateUserStatus(Long id, boolean enabled) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setEnabled(enabled);
        userRepository.save(user);
        return "User status updated";
    }
}
