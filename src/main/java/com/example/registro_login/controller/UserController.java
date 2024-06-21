package com.example.registro_login.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    // fields? Of what type? Probably of the UserService

    // Autowired annotation above a constructor of the fields above this

    // Aqui iria un metodo PostMapping para registrar el usuario, pero no se como hacerlo sin el service.

    // Aqui iria otro metodo PostMapping que seria para hacer login, no se como implementarlo ni aqui ni el service

    // Por ultimo otro metodo PostMapping que seria el llamado a el reset de la contraseña

}
