package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeAtOrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.EmployeeAtOrganizationEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.EmployeeAtOrganizationRepository;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.EmployeeAtOrganizationDataSource;
import io.vavr.control.Either;
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
    public Either<RuntimeException, EmployeeAtOrganizationDTO> findById(UUID id) {
        return employeeAtOrganizationDataSource.findById(id).<Either<RuntimeException, EmployeeAtOrganizationDTO>>map(entity -> Either.right(new EmployeeAtOrganizationDTO(entity)))
                .orElseGet(() -> Either.left(new ObjectNotFoundException(
                        "Emprego na orginação não encontrado! Id: " + id + ", Tipo: "
                                + EmployeeAtOrganizationDTO.class.getName())));
    }

    @Override
    public Either<RuntimeException, EmployeeAtOrganizationDTO> create(EmployeeAtOrganizationDTO param) {
        return Either.right(new EmployeeAtOrganizationDTO(employeeAtOrganizationDataSource.save(param.toEntity())));
    }

    @Override
    public Either<RuntimeException, EmployeeAtOrganizationDTO> update(UUID id, EmployeeAtOrganizationDTO param) {
        throw new NotImplementedException();
    }

    @Override
    public Either<RuntimeException, EmployeeAtOrganizationDTO> disable(UUID id) {
        final EmployeeAtOrganizationEntity employeeAtOrganizationEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                EmployeeAtOrganizationDTO::toEntity);
        employeeAtOrganizationEntity.setStatus(false);
        return Either.right(new EmployeeAtOrganizationDTO(employeeAtOrganizationDataSource.save(employeeAtOrganizationEntity)));
    }

    @Override
    public Either<RuntimeException, EmployeeAtOrganizationDTO> enable(UUID id) {
        final EmployeeAtOrganizationEntity employeeAtOrganizationEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                EmployeeAtOrganizationDTO::toEntity);
        employeeAtOrganizationEntity.setStatus(true);
        return Either.right(new EmployeeAtOrganizationDTO(employeeAtOrganizationDataSource.save(employeeAtOrganizationEntity)));
    }

    @Override
    public Either<RuntimeException, Long> count() {
        return Either.right(employeeAtOrganizationDataSource.count());
    }

}
