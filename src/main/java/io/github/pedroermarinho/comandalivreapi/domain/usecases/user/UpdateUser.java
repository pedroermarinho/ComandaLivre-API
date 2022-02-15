package io.github.pedroermarinho.comandalivreapi.domain.usecases.user;

import java.util.UUID;

import org.springframework.stereotype.Service;

import io.github.pedroermarinho.comandalivreapi.domain.entities.User;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.EmailValidation;

@Service
public class UpdateUser {
    
    private final UserRepository userRepository;

    public UpdateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(UUID id, User userParam){
        EmailValidation.validationThrow(userParam.getEmail());
        return userRepository.update(id, userParam);
    }
}
