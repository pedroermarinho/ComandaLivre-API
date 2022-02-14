package io.github.pedroermarinho.comandalivreapi.domain.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import io.github.pedroermarinho.comandalivreapi.domain.entities.User;

@Component
public interface UserRepository {
    List<User> findAll();
    User findById(UUID id);
    User findByEmail(String email);
    User create(User user);
    User update(UUID id,User user);
    void disable(User user);
}
