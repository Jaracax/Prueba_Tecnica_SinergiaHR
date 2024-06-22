package com.example.registro_login.rest;

import com.example.registro_login.rest.request.LoginRequest;
import com.example.registro_login.rest.request.RegisterRequest;
import com.example.registro_login.rest.request.ResetRequest;
import com.example.registro_login.rest.response.LoginResponse;
import com.example.registro_login.rest.response.RegisterResponse;
import com.example.registro_login.rest.response.ResetResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    // fields? Of what type? Probably of the UserService

    // Autowired annotation above a constructor of the fields above this

    @PostMapping("/resetPassword")
    public ResponseEntity<ResetResponse> processResetPassword(@RequestBody ResetRequest request){
        ResetResponse response = new ResetResponse();
        response.setPassword("new password"); // No devolerle la password al usuario

        return ResponseEntity.ok(response);

//        public (no se que deberia devolver) resetPassword(@RequestParam String email) {
//            userService.resetPassword(email);
//            return aca deberia mandarle la nueva contraseña al mail, ni idea de como hacer eso.
    }

    @PostMapping("/registration")
    public ResponseEntity<RegisterResponse> processIncomingRegisterRequest(@RequestBody RegisterRequest request){
        RegisterResponse response = new RegisterResponse();
        response.setName("Daniel");
        response.setLastName("Chirinos");
        response.setEmail("example@gmal.com");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);



        //    public entity register/registration(@RequestBody Entity entity){
//        Entity entity = userService.save;
//        return entity; Creo que deberia retornarla pero no estoy seguro
//    }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> processIncomingLoginRequest(@RequestBody LoginRequest request) {

        LoginResponse response = new LoginResponse();
        response.setName("Jose");
        response.setLastName("Dominguez");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);



        //        public (no se que deberia devolver) login(@RequestParam String email, @RequestParam String password){
//        Optional<Entity> entity = userService.findUserByEmailAndPassword;
//        if(entity.isPresent()) {
//            Logging successful que no se como hacer
//            }
//        } else email o contraseña incorrecto
    }
}
