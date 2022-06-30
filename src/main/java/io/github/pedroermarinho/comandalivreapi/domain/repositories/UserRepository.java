package io.github.pedroermarinho.comandalivreapi.domain.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
import io.vavr.control.Either;

public interface UserRepository extends GenericRepository<UserDTO> {

    Either<RuntimeException, UserDTO> findByEmail(String email);

    Either<RuntimeException, UserDTO> findByUsername(String username);

    Either<RuntimeException, Boolean> existsByUsername(String username);

    Either<RuntimeException, Boolean> existsByEmail(String email);
}
