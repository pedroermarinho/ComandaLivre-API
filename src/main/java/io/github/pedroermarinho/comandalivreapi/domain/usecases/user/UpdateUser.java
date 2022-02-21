package io.github.pedroermarinho.comandalivreapi.domain.usecases.user;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.pedroermarinho.comandalivreapi.domain.entities.UserEntity;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.EmailValidation;

@Service
public class UpdateUser {
    
    private final UserRepository userRepository;

    public UpdateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserEntity execute(UUID id, UserEntity userParam){
        EmailValidation.validationThrow(userParam.getEmail());
        return userRepository.update(id, userParam);
    }
}
