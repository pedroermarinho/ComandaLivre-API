package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.entities.RoleEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.record.RoleRecord;
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
    public List<RoleRecord> findAll() {
        return roleDataSource.findAll().stream().map(RoleRecord::new).toList();
    }

    @Override
    public Either<RuntimeException, RoleRecord> findById(UUID id) {
        return roleDataSource.findById(id).<Either<RuntimeException, RoleRecord>>map(entity -> Either.right(new RoleRecord(entity)))
                .orElseGet(() -> Either.left(new ObjectNotFoundException(
                        translator.toLocale("role.ObjectNotFoundException"))));
    }

    @Override
    public Either<RuntimeException, RoleRecord> create(RoleRecord param) {
        return Either.right(new RoleRecord(roleDataSource.save(param.toEntity())));
    }

    @Override
    public Either<RuntimeException, RoleRecord> update(UUID id, RoleRecord param) {
        throw new NotImplementedException();
    }

    @Override
    public Either<RuntimeException, RoleRecord> disable(UUID id) {
        final RoleEntity roleEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                RoleRecord::toEntity);
        roleEntity.setStatus(false);
        return Either.right(new RoleRecord(roleDataSource.save(roleEntity)));
    }

    @Override
    public Either<RuntimeException, RoleRecord> enable(UUID id) {
        final RoleEntity roleEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                RoleRecord::toEntity);
        roleEntity.setStatus(false);
        return Either.right(new RoleRecord(roleDataSource.save(roleEntity)));
    }

    @Override
    public Either<RuntimeException, Long> count() {
        return Either.right(roleDataSource.count());
    }

}
