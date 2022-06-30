package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.RoleDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.RoleEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.RoleRepository;
import io.github.pedroermarinho.comandalivreapi.infra.config.Translator;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.RoleDataSource;
import io.vavr.control.Either;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class RoleRepositoryImpl implements RoleRepository {

    private final RoleDataSource roleDataSource;
    private final Translator translator;

    public RoleRepositoryImpl(RoleDataSource roleDataSource, Translator translator) {
        this.roleDataSource = roleDataSource;
        this.translator = translator;
    }

    @Override
    public List<RoleDTO> findAll() {
        return roleDataSource.findAll().stream().map(RoleDTO::new).toList();
    }

    @Override
    public Either<RuntimeException, RoleDTO> findById(UUID id) {
        return roleDataSource.findById(id).<Either<RuntimeException, RoleDTO>>map(entity -> Either.right(new RoleDTO(entity)))
                .orElseGet(() -> Either.left(new ObjectNotFoundException(
                        translator.toLocale("role.ObjectNotFoundException"))));
    }

    @Override
    public Either<RuntimeException, RoleDTO> create(RoleDTO param) {
        return Either.right(new RoleDTO(roleDataSource.save(param.toEntity())));
    }

    @Override
    public Either<RuntimeException, RoleDTO> update(UUID id, RoleDTO param) {
        throw new NotImplementedException();
    }

    @Override
    public Either<RuntimeException, RoleDTO> disable(UUID id) {
        final RoleEntity roleEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                RoleDTO::toEntity);
        roleEntity.setStatus(false);
        return Either.right(new RoleDTO(roleDataSource.save(roleEntity)));
    }

    @Override
    public Either<RuntimeException, RoleDTO> enable(UUID id) {
        final RoleEntity roleEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                RoleDTO::toEntity);
        roleEntity.setStatus(false);
        return Either.right(new RoleDTO(roleDataSource.save(roleEntity)));
    }

    @Override
    public Either<RuntimeException, Long> count() {
        return Either.right(roleDataSource.count());
    }

}
