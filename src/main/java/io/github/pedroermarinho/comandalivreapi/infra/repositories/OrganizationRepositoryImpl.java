package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.OrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.OrganizationRepository;
import io.github.pedroermarinho.comandalivreapi.infra.convert.OrganizationConvert;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.OrganizationDataSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class OrganizationRepositoryImpl implements OrganizationRepository {

    private final OrganizationDataSource organizationDataSource;
    private final OrganizationConvert convert = new OrganizationConvert();

    public OrganizationRepositoryImpl(OrganizationDataSource organizationDataSource) {
        this.organizationDataSource = organizationDataSource;
    }

    @Override
    public List<OrganizationDTO> findAll() {
        return convert.formEntity(organizationDataSource.findAll());
    }

    @Override
    public OrganizationDTO findById(UUID id) {
        return convert.formEntity(organizationDataSource.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Organização não encontrado! Id: " + id + ", Tipo: " + OrganizationDTO.class.getName())));
    }

    @Override
    public OrganizationDTO create(OrganizationDTO param) {
        return convert.formEntity(organizationDataSource.save(convert.formDTO(param)));
    }

    @Override
    public OrganizationDTO update(UUID id, OrganizationDTO param) {
        throw new NotImplementedException();
    }

    @Override
    public OrganizationDTO disable(UUID id) {
        final OrganizationDTO organizationDTO = findById(id);
        organizationDTO.setStatus(false);
        return convert.formEntity(organizationDataSource.save(convert.formDTO(organizationDTO)));
    }

    @Override
    public OrganizationDTO enable(UUID id) {
        final OrganizationDTO organizationDTO = findById(id);
        organizationDTO.setStatus(true);
        return convert.formEntity(organizationDataSource.save(convert.formDTO(organizationDTO)));
    }

}
