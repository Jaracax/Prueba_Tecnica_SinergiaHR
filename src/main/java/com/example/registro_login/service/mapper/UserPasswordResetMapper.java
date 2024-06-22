package com.example.registro_login.service.mapper;

import com.example.registro_login.rest.request.ResetRequest;
import com.example.registro_login.service.dto.UserPasswordResetDto;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordResetMapper {

    public UserPasswordResetDto toDto(ResetRequest request) {
        UserPasswordResetDto result = new UserPasswordResetDto();
        result.setEmail(request.getEmail());
        result.setPassword(request.getPassword());
        return result;
    }
}
