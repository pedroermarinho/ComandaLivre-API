package io.github.pedroermarinho.comandalivreapi.domain.usecases.user;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.pedroermarinho.comandalivreapi.domain.entities.UserEntity;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;

@Service
public class StatusUser {

    private final UserRepository userRepository;

    public StatusUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Transactional
    public UserEntity disableUser(UUID id){
        if(id == null){
            throw new IllegalArgumentException("O id não pode ser null");
        }
        return userRepository.disable(id);
    }

    @Transactional
    public UserEntity enableUser(UUID id){
        if(id == null){
            throw new IllegalArgumentException("O id não pode ser null");
        }
        return userRepository.enable(id);
    }
}
