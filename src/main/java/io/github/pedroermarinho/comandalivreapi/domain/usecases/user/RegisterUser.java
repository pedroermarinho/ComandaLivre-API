package io.github.pedroermarinho.comandalivreapi.domain.usecases.user;

import org.springframework.stereotype.Service;

import io.github.pedroermarinho.comandalivreapi.domain.entities.User;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.EmailValidation;

@Service
public class RegisterUser {

    private final UserRepository userRepository;

    public RegisterUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(User userRegister){
        EmailValidation.validationThrow(userRegister.getEmail());
        return userRepository.create(userRegister);
    }

}
