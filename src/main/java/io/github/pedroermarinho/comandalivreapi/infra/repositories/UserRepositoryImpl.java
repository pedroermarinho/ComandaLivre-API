package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.entities.UserEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.UserDataSource;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserDataSource userDataSource;

    public UserRepositoryImpl(UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }

    @Override
    public List<UserEntity> findAll() {
        return userDataSource.findAll();
    }

    @Override
    public UserEntity findById(UUID id) {
        return userDataSource.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Usuário não encontrado! Id: " + id + ", Tipo: " + UserEntity.class.getName()
                )
        );
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userDataSource.findByEmail(email).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Usuário não encontrado! email: " + email + ", Tipo: " + UserEntity.class.getName()
                )
        );
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userDataSource.findByUsername(username).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Usuário não encontrado! username: " + username + ", Tipo: " + UserEntity.class.getName()
                )
        );
    }

    @Override
    public UserEntity create(UserEntity user) {
        return userDataSource.save(user);
    }

    @Override
    public UserEntity update(UUID id, UserEntity userParam) {
        final UserEntity user = findById(id);
        user.setName(userParam.getEmail());
        user.setUsername(userParam.getUsername());
        return userDataSource.save(user);
    }

    @Override
    public UserEntity disable(UUID id) {
        final UserEntity user = findById(id);
        user.setStatus(false);
        return userDataSource.save(user);
    }

    @Override
    public UserEntity enable(UUID id) {
        final UserEntity user = findById(id);
        user.setStatus(true);
        return userDataSource.save(user);
    }

    
}
