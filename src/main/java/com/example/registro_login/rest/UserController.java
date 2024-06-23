package com.example.registro_login.rest;

import com.example.registro_login.rest.request.LoginRequest;
import com.example.registro_login.rest.request.RegisterRequest;
import com.example.registro_login.rest.request.ResetPasswordRequest;
import com.example.registro_login.rest.response.RegisterResponse;
import com.example.registro_login.service.UserService;
import com.example.registro_login.service.mapper.UserLoginMapper;
import com.example.registro_login.service.mapper.UserPasswordResetMapper;
import com.example.registro_login.service.mapper.UserRegistrationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService service;
    private UserLoginMapper loginMapper;
    private UserRegistrationMapper registrationMapper;
    private UserPasswordResetMapper passwordResetMapper;

    @Autowired
    public UserController(UserService service, UserLoginMapper loginMapper,
                          UserRegistrationMapper registrationMapper,
                          UserPasswordResetMapper passwordResetMapper) {
        this.service = service;
        this.loginMapper = loginMapper;
        this.registrationMapper = registrationMapper;
        this.passwordResetMapper = passwordResetMapper;
    }

    @PostMapping("/resetPassword")
    public ResponseEntity processResetPassword(@RequestBody ResetPasswordRequest request){

        if (service.resetPassword(passwordResetMapper.toDto(request))) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<RegisterResponse> processIncomingRegisterRequest(@RequestBody RegisterRequest request){

        if (service.userRegistration(registrationMapper.toDto(request))){
            RegisterResponse registerResponse = new RegisterResponse();
            registerResponse.setName(request.getName());
            registerResponse.setLastName(request.getLastName());
            registerResponse.setEmail(request.getEmail());

            return ResponseEntity.ok(registerResponse);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email o contraseña incorrecto");
        }
    }

    @PostMapping("/login")
    public ResponseEntity processIncomingLoginRequest(@RequestBody LoginRequest request) {

        try{
            service.userLogin(loginMapper.toDto(request));
           return ResponseEntity.ok().build();
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
