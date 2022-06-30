package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.UserEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.UserDataSource;
import io.vavr.control.Either;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserDataSource userDataSource;

    public UserRepositoryImpl(UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }

    @Override
    public List<UserDTO> findAll() {
        return userDataSource.findAll().stream().map(UserDTO::new).toList();
    }


    @Override
    public Either<RuntimeException, UserDTO> findById(UUID id) {
        return userDataSource.findById(id)
                .<Either<RuntimeException, UserDTO>>map(userEntity -> Either.right(new UserDTO(userEntity)))
                .orElseGet(() -> Either.left(new ObjectNotFoundException(
                        "Usuário não encontrado! Id: %s , Tipo: %s ".formatted(id, UserDTO.class.getName())
                )));
    }

    @Override
    public Either<RuntimeException, UserDTO> findByEmail(String email) {
        return userDataSource.findByEmail(email)
                .<Either<RuntimeException, UserDTO>>map(userEntity -> Either.right(new UserDTO(userEntity)))
                .orElseGet(() -> Either.left(new ObjectNotFoundException(
                        "Usuário não encontrado! email: " + email + ", Tipo: " + UserDTO.class.getName()
                )));
    }

    @Override
    public Either<RuntimeException, UserDTO> findByUsername(String username) {
        return userDataSource.findByUsername(username)
                .<Either<RuntimeException, UserDTO>>map(userEntity -> Either.right(new UserDTO(userEntity)))
                .orElseGet(() -> Either.left(new ObjectNotFoundException(
                        "Usuário não encontrado! username: " + username + ", Tipo: " + UserDTO.class.getName()
                )));
    }

    @Override
    public Either<RuntimeException, Boolean> existsByUsername(String username) {
        return Either.right(userDataSource.existsByUsername(username));
    }

    @Override
    public Either<RuntimeException, Boolean> existsByEmail(String email) {
        return Either.right(userDataSource.existsByEmail(email));
    }

    @Override
    public Either<RuntimeException, UserDTO> create(UserDTO user) {
        return Either.right(new UserDTO(userDataSource.save(user.toEntity())));
    }

    @Override
    public Either<RuntimeException, UserDTO> update(UUID id, UserDTO userParam) {
        final UserEntity user = findById(id).fold(throwable -> {
                    throw throwable;
                },
                UserDTO::toEntity
        );
        user.setName(userParam.email());
        user.setUsername(userParam.username());
        return Either.right(new UserDTO(userDataSource.save(user)));
    }

    @Override
    public Either<RuntimeException, UserDTO> disable(UUID id) {
        final UserEntity user = findById(id).fold(throwable -> {
                    throw throwable;
                },
                UserDTO::toEntity
        );
        user.setStatus(false);
        return Either.right(new UserDTO(userDataSource.save(user)));
    }

    @Override
    public Either<RuntimeException, UserDTO> enable(UUID id) {
        final UserEntity user = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                UserDTO::toEntity);
        user.setStatus(true);
        return Either.right(new UserDTO(userDataSource.save(user)));
    }

    @Override
    public Either<RuntimeException, Long> count() {
        return Either.right(userDataSource.count());
    }


}
