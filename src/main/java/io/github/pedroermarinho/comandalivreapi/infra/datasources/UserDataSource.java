package io.github.pedroermarinho.comandalivreapi.infra.datasources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.pedroermarinho.comandalivreapi.domain.entities.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDataSource extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
