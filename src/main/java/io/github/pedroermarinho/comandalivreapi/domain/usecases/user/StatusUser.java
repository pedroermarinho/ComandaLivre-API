package io.github.pedroermarinho.comandalivreapi.domain.usecases.user;

import java.util.UUID;

import org.springframework.stereotype.Service;

import io.github.pedroermarinho.comandalivreapi.domain.entities.User;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;

@Service
public class StatusUser {

    private final UserRepository userRepository;

    public StatusUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User disableUser(UUID id){
        if(id == null){
            throw new IllegalArgumentException("O id não pode ser null");
        }
        return userRepository.disable(id);
    }

    public User enableUser(UUID id){
        if(id == null){
            throw new IllegalArgumentException("O id não pode ser null");
        }
        return userRepository.enable(id);
    }
}
