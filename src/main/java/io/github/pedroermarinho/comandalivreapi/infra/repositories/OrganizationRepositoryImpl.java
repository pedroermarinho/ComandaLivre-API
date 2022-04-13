package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.OrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.OrganizationEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.OrganizationRepository;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.OrganizationDataSource;
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
    public OrganizationDTO findById(UUID id) {
        return new OrganizationDTO(organizationDataSource.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Organização não encontrado! Id: " + id + ", Tipo: " + OrganizationDTO.class.getName())));
    }

    @Override
    public OrganizationDTO create(OrganizationDTO param) {
        return new OrganizationDTO(organizationDataSource.save(param.toEntity()));
    }

    @Override
    public OrganizationDTO update(UUID id, OrganizationDTO param) {
        throw new NotImplementedException();
    }

    @Override
    public OrganizationDTO disable(UUID id) {
        final OrganizationEntity organizationEntity = findById(id).toEntity();
        organizationEntity.setStatus(false);
        return new OrganizationDTO(organizationDataSource.save(organizationEntity));
    }

    @Override
    public OrganizationDTO enable(UUID id) {
        final OrganizationEntity organizationEntity = findById(id).toEntity();
        organizationEntity.setStatus(true);
        return new OrganizationDTO(organizationDataSource.save(organizationEntity));
    }

    @Override
    public long count() {
        return organizationDataSource.count();
    }

}
