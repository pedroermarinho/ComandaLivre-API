package io.github.pedroermarinho.comandalivreapi.domain.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;

public interface UserRepository extends GenericRepository<UserDTO> {

    UserDTO findByEmail(String email);

    UserDTO findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
