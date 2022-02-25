package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.UserDTO;
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
    private final UserConvert convert = new UserConvert();

    public UserRepositoryImpl(UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }

    @Override
    public List<UserDTO> findAll() {
        return convert.formEntity(userDataSource.findAll());
    }

    @Override
    public UserDTO findById(UUID id) {
        return convert.formEntity(userDataSource.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Usuário não encontrado! Id: " + id + ", Tipo: " + UserDTO.class.getName()
                ))
        );
    }

    @Override
    public UserDTO findByEmail(String email) {
        return convert.formEntity(userDataSource.findByEmail(email).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Usuário não encontrado! email: " + email + ", Tipo: " + UserDTO.class.getName()
                ))
        );
    }

    @Override
    public UserDTO findByUsername(String username) {
        return convert.formEntity(userDataSource.findByUsername(username).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Usuário não encontrado! username: " + username + ", Tipo: " + UserDTO.class.getName()
                ))
        );
    }

    @Override
    public UserDTO create(UserDTO user) {
        return convert.formEntity(userDataSource.save(convert.formDTO(user)));
    }

    @Override
    public UserDTO update(UUID id, UserDTO userParam) {
        final UserDTO user = findById(id);
        user.setName(userParam.getEmail());
        user.setUsername(userParam.getUsername());
        return convert.formEntity(userDataSource.save(convert.formDTO(user)));
    }

    @Override
    public UserDTO disable(UUID id) {
        final UserDTO user = findById(id);
        user.setStatus(false);
        return convert.formEntity(userDataSource.save(convert.formDTO(user)));
    }

    @Override
    public UserDTO enable(UUID id) {
        final UserDTO user = findById(id);
        user.setStatus(true);
        return convert.formEntity(userDataSource.save(convert.formDTO(user)));
    }


}
