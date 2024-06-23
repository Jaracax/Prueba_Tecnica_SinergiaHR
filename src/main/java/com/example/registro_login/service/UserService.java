package com.example.registro_login.service;

import com.example.registro_login.persistence.entity.User;
import com.example.registro_login.persistence.repository.UserRepository;
import com.example.registro_login.service.dto.UserLoginDto;
import com.example.registro_login.service.dto.UserPasswordResetDto;
import com.example.registro_login.service.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;
    private static final Set<Character> specialCharactersSet = new HashSet<>();

    static {
        specialCharactersSet.add('!');
        specialCharactersSet.add('@');
        specialCharactersSet.add('#');
        specialCharactersSet.add('$');
        specialCharactersSet.add('%');
        specialCharactersSet.add('^');
        specialCharactersSet.add('&');
        specialCharactersSet.add('*');
        specialCharactersSet.add('(');
        specialCharactersSet.add(')');
        specialCharactersSet.add('_');
        specialCharactersSet.add('+');
        specialCharactersSet.add('-');
        specialCharactersSet.add('=');
        specialCharactersSet.add('[');
        specialCharactersSet.add(']');
        specialCharactersSet.add('{');
        specialCharactersSet.add('}');
        specialCharactersSet.add(';');
        specialCharactersSet.add(':');
        specialCharactersSet.add('\'');
        specialCharactersSet.add('"');
        specialCharactersSet.add('\\');
        specialCharactersSet.add('|');
        specialCharactersSet.add(',');
        specialCharactersSet.add('.');
        specialCharactersSet.add('<');
        specialCharactersSet.add('>');
        specialCharactersSet.add('/');
        specialCharactersSet.add('?');
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean resetPassword(UserPasswordResetDto userPasswordResetDto) {

        Optional<User> optionalUser = userRepository.findByEmail(userPasswordResetDto.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (validatePassword(userPasswordResetDto.getPassword())){
                user.setPassword(userPasswordResetDto.getPassword());
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public boolean userRegistration(UserRegistrationDto userRegistrationDto){

        if (validateEmail(userRegistrationDto.getEmail()) && validatePassword(userRegistrationDto.getPassword())){
            User user = new User(userRegistrationDto.getName(), userRegistrationDto.getLastName(),
                    userRegistrationDto.getEmail(), userRegistrationDto.getPassword());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    private boolean validateEmail(String email){
        return email != null && email.contains("@");
    }

    private boolean validatePassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasLowercase = false;
        boolean hasUppercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (isSpecialCharacter(c)) {
                hasSpecialChar = true;
            }
        }

        return hasLowercase && hasUppercase && hasDigit && hasSpecialChar;
    }

    private boolean isSpecialCharacter(char ch) {
        return specialCharactersSet.contains(ch);
    }

    public boolean userLogin(UserLoginDto userLoginDto) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(userLoginDto.getEmail());
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (user.getPassword().equals(userLoginDto.getPassword())){
                return true;
            }
        }
        throw new Exception("email/contraseña incorrecto");
    }
}