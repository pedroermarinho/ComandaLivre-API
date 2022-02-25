package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.RoleDTO;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.RoleRepository;
import io.github.pedroermarinho.comandalivreapi.infra.convert.RoleConvert;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.RoleDataSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class RoleRepositoryImpl implements RoleRepository {

    private final RoleDataSource roleDataSource;
    private final RoleConvert convert = new RoleConvert();

    public RoleRepositoryImpl(RoleDataSource roleDataSource) {
        this.roleDataSource = roleDataSource;
    }

    @Override
    public List<RoleDTO> findAll() {
        return convert.formEntity(roleDataSource.findAll());
    }

    @Override
    public RoleDTO findById(UUID id) {
        return convert.formEntity(roleDataSource.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Cargo não encontrado! Id: " + id + ", Tipo: " + RoleDTO.class.getName())));
    }

    @Override
    public RoleDTO create(RoleDTO param) {
        return convert.formEntity(roleDataSource.save(convert.formDTO(param)));
    }

    @Override
    public RoleDTO update(UUID id, RoleDTO param) {
        throw new NotImplementedException();
    }

    @Override
    public RoleDTO disable(UUID id) {
        final RoleDTO roleDTO = findById(id);
        roleDTO.setStatus(false);
        return convert.formEntity(roleDataSource.save(convert.formDTO(roleDTO)));
    }

    @Override
    public RoleDTO enable(UUID id) {
        final RoleDTO roleDTO = findById(id);
        roleDTO.setStatus(true);
        return convert.formEntity(roleDataSource.save(convert.formDTO(roleDTO)));
    }

}
