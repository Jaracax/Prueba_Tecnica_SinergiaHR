package com.example.registro_login.rest;

import com.example.registro_login.rest.request.LoginRequest;
import com.example.registro_login.rest.request.RegisterRequest;
import com.example.registro_login.rest.request.ResetRequest;
import com.example.registro_login.rest.response.LoginResponse;
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

    // fields? Of what type? Probably of the UserService
    private UserService service;
    private UserLoginMapper loginMapper;
    private UserRegistrationMapper registrationMapper;
    private UserPasswordResetMapper passwordResetMapper;

    @Autowired
    public UserController(UserService service, UserLoginMapper loginMapper, UserRegistrationMapper registrationMapper, UserPasswordResetMapper passwordResetMapper) {
        this.service = service;
        this.loginMapper = loginMapper;
        this.registrationMapper = registrationMapper;
        this.passwordResetMapper = passwordResetMapper;
    }

    // Autowired annotation above a constructor of the fields above this

    @PostMapping("/resetPassword")
    public ResponseEntity processResetPassword(@RequestBody ResetRequest request){

        // si con los datos de entrada, pude cambiar la contraseña
        if (service.resetPassword(passwordResetMapper.toDto(request))) {
            return ResponseEntity.ok().build(); // Todo bien
        } else {
            // si no pude
            return ResponseEntity.badRequest().build(); // todo mal
        }
//        public (no se que deberia devolver) resetPassword(@RequestParam String email) {
//            userService.resetPassword(email);
//            return aca deberia mandarle la nueva contraseña al mail, ni idea de como hacer eso.
    }

    @PostMapping("/registration")
    public ResponseEntity<RegisterResponse> processIncomingRegisterRequest(@RequestBody RegisterRequest request){

        // No hay verificacion para el nombre y apellido
        // Se verifica que el email y la contraseña cumplan con las verificaciones
        if (service.userRegistration(registrationMapper.toDto(request))){
            RegisterResponse registerResponse = new RegisterResponse();
            registerResponse.setEmail(request.getEmail());
            registerResponse.setName(request.getName());
            registerResponse.setLastName(request.getLastName());
            return ResponseEntity.ok(registerResponse); // Todo bien
        } else return ResponseEntity.badRequest().build(); // todo mal

        //        RegisterResponse response = new RegisterResponse();
//        response.setName("Daniel");
//        response.setLastName("Chirinos");
//        response.setEmail("example@gmal.com");
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);



        //    public entity register/registration(@RequestBody Entity entity){
//        Entity entity = userService.save;
//        return entity; Creo que deberia retornarla pero no estoy seguro
//    }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> processIncomingLoginRequest(@RequestBody LoginRequest request) {

        // Si el email y la contraseña son validos

        try{
           return ResponseEntity.ok(service.userLogin(loginMapper.toDto(request))); // Todo bien
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email o contraseña incorrecto");
        }

        // Si todo mal lanzar el mensaje de usuario o contraseña incorrecto
        //        public (no se que deberia devolver) login(@RequestParam String email, @RequestParam String password){
//        Optional<Entity> entity = userService.findUserByEmailAndPassword;
//        if(entity.isPresent()) {
//            Logging successful que no se como hacer
//            }
//        } else email o contraseña incorrecto
    }
}
