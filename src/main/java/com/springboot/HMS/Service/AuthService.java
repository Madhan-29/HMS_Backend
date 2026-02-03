package com.springboot.HMS.Service;

import com.springboot.HMS.dto.LoginRequestDTO;

public interface AuthService {
    String login(LoginRequestDTO request);
}
