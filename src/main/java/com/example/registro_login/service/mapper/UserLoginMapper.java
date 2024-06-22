package com.example.registro_login.service.mapper;

import com.example.registro_login.rest.request.LoginRequest;
import com.example.registro_login.service.dto.UserLoginDto;
import org.springframework.stereotype.Component;

@Component
public class UserLoginMapper {

    public UserLoginDto toDto(LoginRequest request) {
        UserLoginDto result = new UserLoginDto();
        result.setEmail(request.getEmail());
        result.setPassword(request.getPassword());
        return result;
    }
}
