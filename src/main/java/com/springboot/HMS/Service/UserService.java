package com.springboot.HMS.Service;

import java.util.List;

import com.springboot.HMS.Entity.UserEntity;

public interface UserService {

    List<UserEntity> getAllUsers();

    UserEntity getMyProfile(String email);

    String deleteUser(Long id);

    String updateUserStatus(Long id, boolean enabled);
}
