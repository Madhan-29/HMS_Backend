package com.springboot.HMS.Controller;

import org.springframework.web.bind.annotation.*;

import com.springboot.HMS.Entity.UserEntity;
import com.springboot.HMS.Service.RegisterService;

@RestController
@RequestMapping("/auth")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public String register(@RequestBody UserEntity user) {
        return registerService.register(user);
    }
}
