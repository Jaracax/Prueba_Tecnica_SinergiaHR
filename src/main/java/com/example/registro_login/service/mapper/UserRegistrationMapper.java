package com.example.registro_login.service.mapper;

import com.example.registro_login.rest.request.RegisterRequest;
import com.example.registro_login.service.dto.UserRegistrationDto;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationMapper {

    public UserRegistrationDto toDto(RegisterRequest request) {
        UserRegistrationDto result = new UserRegistrationDto();
        result.setEmail(request.getEmail());
        result.setPassword(request.getPassword());
        return result;
    }
}
