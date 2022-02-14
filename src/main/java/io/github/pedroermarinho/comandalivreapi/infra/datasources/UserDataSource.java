package io.github.pedroermarinho.comandalivreapi.infra.datasources;

import io.github.pedroermarinho.comandalivreapi.domain.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDataSource extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);
}
