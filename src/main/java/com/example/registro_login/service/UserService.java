package com.example.registro_login.service;

import com.example.registro_login.service.dto.UserLoginDto;
import com.example.registro_login.service.dto.UserPasswordResetDto;
import com.example.registro_login.service.dto.UserRegistrationDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // Aca iria un field de tipo repository de la entity

    // Un constructor con la annotation autowired

    public boolean resetPassword(UserPasswordResetDto userPasswordResetDto) {

        // si email pertenece a un usiario registrado
        // devolver de la DB un usuario por el email
        // si no existe ninguno (Optional.isEmpty()) return false;
        // actualizarlo en la db
        // repository.save
        // enviar un mail al usuario confirmando que su clave fue cambiada con éxito
        return true; // todo bien
    }

    public boolean userRegistration(UserRegistrationDto userRegistrationDto){


        // No hay verificacion para el nombre y el apellido
        // Se verifica que el mail incluya un "@"
        // Se verifica que la contraseña
        // incluya al menos una letra mayuscula, miniscula,
        // numero, símbolo y que su longitud no sea mayor a 8 caracteres
        // Si todo se cumple, hacer repository.save
        // Si el mail es invalido, dejar saber que el mail le falta el "@" si la contraseña falla dejar saber.
        return true;
    }

    public boolean userLogin(UserLoginDto userLoginDto){

        // Se verifica que el email que se usa en el login exista en la db
        // Si existe verificar que ese email este asociado a la contraseña que viene del dto
        // Si todo bien, no se.
        // Si todo mal lanzar mensaje de "usuario/contraseña incorrecto
        return true;
    }



}
