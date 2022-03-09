package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeAtOrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.EmployeeAtOrganizationEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.EmployeeAtOrganizationRepository;
import io.github.pedroermarinho.comandalivreapi.infra.convert.EmployeeAtOrganizationConvert;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.EmployeeAtOrganizationDataSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class EmployeeAtOrganizationRepositoryImpl implements EmployeeAtOrganizationRepository {

    private final EmployeeAtOrganizationDataSource employeeAtOrganizationDataSource;

    public EmployeeAtOrganizationRepositoryImpl(EmployeeAtOrganizationDataSource employeeAtOrganizationDataSource) {
        this.employeeAtOrganizationDataSource = employeeAtOrganizationDataSource;
    }

    @Override
    public List<EmployeeAtOrganizationDTO> findAll() {
        return employeeAtOrganizationDataSource.findAll().stream().map(EmployeeAtOrganizationDTO::new).toList();
    }

    @Override
    public EmployeeAtOrganizationDTO findById(UUID id) {
        return new EmployeeAtOrganizationDTO(employeeAtOrganizationDataSource.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Emprego na orginação não encontrado! Id: " + id + ", Tipo: "
                                + EmployeeAtOrganizationDTO.class.getName())));
    }

    @Override
    public EmployeeAtOrganizationDTO create(EmployeeAtOrganizationDTO param) {
        return new EmployeeAtOrganizationDTO(employeeAtOrganizationDataSource.save(param.toEntity()));
    }

    @Override
    public EmployeeAtOrganizationDTO update(UUID id, EmployeeAtOrganizationDTO param) {
        throw new NotImplementedException();
    }

    @Override
    public EmployeeAtOrganizationDTO disable(UUID id) {
        final EmployeeAtOrganizationEntity employeeAtOrganizationEntity = findById(id).toEntity();
        employeeAtOrganizationEntity.setStatus(false);
        return new EmployeeAtOrganizationDTO(employeeAtOrganizationDataSource.save(employeeAtOrganizationEntity));
    }

    @Override
    public EmployeeAtOrganizationDTO enable(UUID id) {
        final EmployeeAtOrganizationEntity employeeAtOrganizationEntity = findById(id).toEntity();
        employeeAtOrganizationEntity.setStatus(true);
        return new EmployeeAtOrganizationDTO(employeeAtOrganizationDataSource.save(employeeAtOrganizationEntity));
    }

}
