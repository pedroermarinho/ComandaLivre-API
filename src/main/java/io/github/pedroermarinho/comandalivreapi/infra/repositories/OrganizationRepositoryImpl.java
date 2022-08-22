package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.entities.OrganizationEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.record.OrganizationRecord;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.OrganizationRepository;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.OrganizationDataSource;
import io.vavr.control.Either;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class OrganizationRepositoryImpl implements OrganizationRepository {

    private final OrganizationDataSource organizationDataSource;

    public OrganizationRepositoryImpl(OrganizationDataSource organizationDataSource) {
        this.organizationDataSource = organizationDataSource;
    }

    @Override
    public List<OrganizationRecord> findAll() {
        return organizationDataSource.findAll().stream().map(OrganizationRecord::new).toList();
    }

    @Override
    public Either<RuntimeException, OrganizationRecord> findById(UUID id) {
        return organizationDataSource.findById(id).<Either<RuntimeException, OrganizationRecord>>map(entity -> Either.right(new OrganizationRecord(entity)))
                .orElseGet(() -> Either.left(new ObjectNotFoundException(
                        "Organização não encontrado! Id: " + id + ", Tipo: " + OrganizationRecord.class.getName())));
    }

    @Override
    public Either<RuntimeException, OrganizationRecord> create(OrganizationRecord param) {
        return Either.right(new OrganizationRecord(organizationDataSource.save(param.toEntity())));
    }

    @Override
    public Either<RuntimeException, OrganizationRecord> update(UUID id, OrganizationRecord param) {
        throw new NotImplementedException();
    }

    @Override
    public Either<RuntimeException, OrganizationRecord> disable(UUID id) {
        final OrganizationEntity organizationEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                OrganizationRecord::toEntity);
        organizationEntity.setStatus(false);
        return Either.right(new OrganizationRecord(organizationDataSource.save(organizationEntity)));
    }

    @Override
    public Either<RuntimeException, OrganizationRecord> enable(UUID id) {
        final OrganizationEntity organizationEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                OrganizationRecord::toEntity);
        organizationEntity.setStatus(true);
        return Either.right(new OrganizationRecord(organizationDataSource.save(organizationEntity)));
    }

    @Override
    public Either<RuntimeException, Long> count() {
        return Either.right(organizationDataSource.count());
    }

}
