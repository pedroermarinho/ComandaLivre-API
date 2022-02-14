package io.github.pedroermarinho.comandalivreapi.domain.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.Entities.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository {
    List<User> findAll();
    User findById(UUID id);
    User findByEmail(String email);
    User create(User user);
    User update(UUID id,User user);
    void disable(User user);
}
