package io.github.pedroermarinho.comandalivreapi.domain.usecases.user;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.github.pedroermarinho.comandalivreapi.domain.entities.UserEntity;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.EmailValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UsernameValidation;

@Service
public class SearchUser {

    private final UserRepository userRepository;

    public SearchUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity searchUserById(UUID id){
        if(id == null){
            throw new IllegalArgumentException("O id não pode ser null");
        }
        return userRepository.findById(id);
    }

    public UserEntity searchUserByEmail(String email){
        if(email == null){
            throw new IllegalArgumentException("O email não pode ser null");
        }
        new EmailValidation().validationThrow(email);
        return userRepository.findByEmail(email);
    }

    public UserEntity searchUserByUsername(String username){
        if(username == null){
            throw new IllegalArgumentException("O username não pode ser null");
        }
        new UsernameValidation().validationThrow(username);
        return userRepository.findByUsername(username);
    }

    public List<UserEntity> searchUserAll(){
        return userRepository.findAll();
    }
    
}
