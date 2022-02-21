package io.github.pedroermarinho.comandalivreapi.domain.usecases.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.pedroermarinho.comandalivreapi.domain.entities.UserEntity;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.EmailValidation;

@Service
public class RegisterUser {

    private final UserRepository userRepository;

    public RegisterUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserEntity execute(UserEntity userRegister){
        EmailValidation.validationThrow(userRegister.getEmail());
        return userRepository.create(userRegister);
    }

}
