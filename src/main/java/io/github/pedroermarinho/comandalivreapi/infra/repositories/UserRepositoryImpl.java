package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.UserEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.UserRepository;
import io.github.pedroermarinho.comandalivreapi.infra.convert.UserConvert;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.UserDataSource;
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
    public UserDTO findById(UUID id) {
        return new UserDTO(userDataSource.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Usuário não encontrado! Id: " + id + ", Tipo: " + UserDTO.class.getName()
                ))
        );
    }

    @Override
    public UserDTO findByEmail(String email) {
        return new UserDTO(userDataSource.findByEmail(email).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Usuário não encontrado! email: " + email + ", Tipo: " + UserDTO.class.getName()
                ))
        );
    }

    @Override
    public UserDTO findByUsername(String username) {
        return new UserDTO(userDataSource.findByUsername(username).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Usuário não encontrado! username: " + username + ", Tipo: " + UserDTO.class.getName()
                ))
        );
    }

    @Override
    public boolean existsByUsername(String username) {
        return userDataSource.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userDataSource.existsByEmail(email);
    }

    @Override
    public UserDTO create(UserDTO user) {
        return new UserDTO(userDataSource.save(user.toEntity()));
    }

    @Override
    public UserDTO update(UUID id, UserDTO userParam) {
        final UserEntity user = findById(id).toEntity();
        user.setName(userParam.email());
        user.setUsername(userParam.username());
        return new UserDTO(userDataSource.save(user));
    }

    @Override
    public UserDTO disable(UUID id) {
        final UserEntity user = findById(id).toEntity();
        user.setStatus(false);
        return new UserDTO(userDataSource.save(user));
    }

    @Override
    public UserDTO enable(UUID id) {
        final UserEntity user = findById(id).toEntity();
        user.setStatus(true);
        return new UserDTO(userDataSource.save(user));
    }


}
