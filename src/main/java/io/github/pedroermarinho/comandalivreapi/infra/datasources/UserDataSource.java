package io.github.pedroermarinho.comandalivreapi.infra.datasources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.pedroermarinho.comandalivreapi.domain.entities.UserEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDataSource extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
}
