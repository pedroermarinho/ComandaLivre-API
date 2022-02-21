package io.github.pedroermarinho.comandalivreapi.domain.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import io.github.pedroermarinho.comandalivreapi.domain.entities.UserEntity;

public interface UserRepository {
    List<UserEntity> findAll();
    UserEntity findById(UUID id);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
    UserEntity create(UserEntity user);
    UserEntity update(UUID id,UserEntity userParam);
    UserEntity disable(UUID id);
    UserEntity enable(UUID id);
}
