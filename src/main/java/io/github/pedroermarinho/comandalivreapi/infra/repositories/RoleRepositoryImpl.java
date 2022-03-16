package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.RoleDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.RoleEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.RoleRepository;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.RoleDataSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class RoleRepositoryImpl implements RoleRepository {

    private final RoleDataSource roleDataSource;

    public RoleRepositoryImpl(RoleDataSource roleDataSource) {
        this.roleDataSource = roleDataSource;
    }

    @Override
    public List<RoleDTO> findAll() {
        return roleDataSource.findAll().stream().map(RoleDTO::new).toList();
    }

    @Override
    public RoleDTO findById(UUID id) {
        return new RoleDTO(roleDataSource.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Cargo não encontrado! Id: " + id + ", Tipo: " + RoleDTO.class.getName())));
    }

    @Override
    public RoleDTO create(RoleDTO param) {
        return new RoleDTO(roleDataSource.save(param.toEntity()));
    }

    @Override
    public RoleDTO update(UUID id, RoleDTO param) {
        throw new NotImplementedException();
    }

    @Override
    public RoleDTO disable(UUID id) {
        final RoleEntity roleEntity = findById(id).toEntity();
        roleEntity.setStatus(false);
        return new RoleDTO(roleDataSource.save(roleEntity));
    }

    @Override
    public RoleDTO enable(UUID id) {
        final RoleEntity roleEntity = findById(id).toEntity();
        roleEntity.setStatus(false);
        return new RoleDTO(roleDataSource.save(roleEntity));
    }

}
