package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.entities.User;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.UserDataSource;

import java.util.List;
import java.util.UUID;

public class UserRepositoryImpl implements UserRepository {

    private final UserDataSource userDataSource;

    public UserRepositoryImpl(UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }

    @Override
    public List<User> findAll() {
        return userDataSource.findAll();
    }

    @Override
    public User findById(UUID id) {
        return userDataSource.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Usuário não encontrado! Id: " + id + ", Tipo: " + User.class.getName()
                )
        );
    }

    @Override
    public User findByEmail(String email) {
        return userDataSource.findByEmail(email).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Usuário não encontrado! email: " + email + ", Tipo: " + User.class.getName()
                )
        );
    }

    @Override
    public User findByUsername(String username) {
        return userDataSource.findByUsername(username).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Usuário não encontrado! username: " + username + ", Tipo: " + User.class.getName()
                )
        );
    }

    @Override
    public User create(User user) {
        return userDataSource.save(user);
    }

    @Override
    public User update(UUID id, User userParam) {
        final User user = findById(id);
        user.setName(userParam.getEmail());
        user.setUsername(userParam.getUsername());
        return userDataSource.save(user);
    }

    @Override
    public User disable(UUID id) {
        final User user = findById(id);
        user.setStatus(false);
        return userDataSource.save(user);
    }

    @Override
    public User enable(UUID id) {
        final User user = findById(id);
        user.setStatus(true);
        return userDataSource.save(user);
    }

    
}
