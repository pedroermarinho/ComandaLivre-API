package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.entities.UserEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.record.UserRecord;
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
    public List<UserRecord> findAll() {
        return userDataSource.findAll().stream().map(UserRecord::new).toList();
    }


    @Override
    public Either<RuntimeException, UserRecord> findById(UUID id) {
        return userDataSource.findById(id)
                .<Either<RuntimeException, UserRecord>>map(userEntity -> Either.right(new UserRecord(userEntity)))
                .orElseGet(() -> Either.left(new ObjectNotFoundException(
                        "Usuário não encontrado! Id: %s , Tipo: %s ".formatted(id, UserRecord.class.getName())
                )));
    }

    @Override
    public Either<RuntimeException, UserRecord> findByEmail(String email) {
        return userDataSource.findByEmail(email)
                .<Either<RuntimeException, UserRecord>>map(userEntity -> Either.right(new UserRecord(userEntity)))
                .orElseGet(() -> Either.left(new ObjectNotFoundException(
                        "Usuário não encontrado! email: " + email + ", Tipo: " + UserRecord.class.getName()
                )));
    }

    @Override
    public Either<RuntimeException, UserRecord> findByUsername(String username) {
        return userDataSource.findByUsername(username)
                .<Either<RuntimeException, UserRecord>>map(userEntity -> Either.right(new UserRecord(userEntity)))
                .orElseGet(() -> Either.left(new ObjectNotFoundException(
                        "Usuário não encontrado! username: " + username + ", Tipo: " + UserRecord.class.getName()
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
    public Either<RuntimeException, UserRecord> create(UserRecord user) {
        return Either.right(new UserRecord(userDataSource.save(user.toEntity())));
    }

    @Override
    public Either<RuntimeException, UserRecord> update(UUID id, UserRecord userParam) {
        final UserEntity user = findById(id).fold(throwable -> {
                    throw throwable;
                },
                UserRecord::toEntity
        );
        user.setName(userParam.email());
        user.setUsername(userParam.username());
        return Either.right(new UserRecord(userDataSource.save(user)));
    }

    @Override
    public Either<RuntimeException, UserRecord> disable(UUID id) {
        final UserEntity user = findById(id).fold(throwable -> {
                    throw throwable;
                },
                UserRecord::toEntity
        );
        user.setStatus(false);
        return Either.right(new UserRecord(userDataSource.save(user)));
    }

    @Override
    public Either<RuntimeException, UserRecord> enable(UUID id) {
        final UserEntity user = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                UserRecord::toEntity);
        user.setStatus(true);
        return Either.right(new UserRecord(userDataSource.save(user)));
    }

    @Override
    public Either<RuntimeException, Long> count() {
        return Either.right(userDataSource.count());
    }


}
