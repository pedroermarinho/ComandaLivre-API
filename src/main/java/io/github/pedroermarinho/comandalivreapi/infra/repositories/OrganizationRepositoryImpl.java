package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.OrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.OrganizationEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
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
    public List<OrganizationDTO> findAll() {
        return organizationDataSource.findAll().stream().map(OrganizationDTO::new).toList();
    }

    @Override
    public Either<RuntimeException, OrganizationDTO> findById(UUID id) {
        return organizationDataSource.findById(id).<Either<RuntimeException, OrganizationDTO>>map(entity -> Either.right(new OrganizationDTO(entity)))
                .orElseGet(() -> Either.left(new ObjectNotFoundException(
                        "Organização não encontrado! Id: " + id + ", Tipo: " + OrganizationDTO.class.getName())));
    }

    @Override
    public Either<RuntimeException, OrganizationDTO> create(OrganizationDTO param) {
        return Either.right(new OrganizationDTO(organizationDataSource.save(param.toEntity())));
    }

    @Override
    public Either<RuntimeException, OrganizationDTO> update(UUID id, OrganizationDTO param) {
        throw new NotImplementedException();
    }

    @Override
    public Either<RuntimeException, OrganizationDTO> disable(UUID id) {
        final OrganizationEntity organizationEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                OrganizationDTO::toEntity);
        organizationEntity.setStatus(false);
        return Either.right(new OrganizationDTO(organizationDataSource.save(organizationEntity)));
    }

    @Override
    public Either<RuntimeException, OrganizationDTO> enable(UUID id) {
        final OrganizationEntity organizationEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                OrganizationDTO::toEntity);
        organizationEntity.setStatus(true);
        return Either.right(new OrganizationDTO(organizationDataSource.save(organizationEntity)));
    }

    @Override
    public Either<RuntimeException, Long> count() {
        return Either.right(organizationDataSource.count());
    }

}
