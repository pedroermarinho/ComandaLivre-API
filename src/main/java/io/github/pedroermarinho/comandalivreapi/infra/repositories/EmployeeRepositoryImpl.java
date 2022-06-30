package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.EmployeeEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.EmployeeRepository;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.EmployeeDataSource;
import io.vavr.control.Either;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EmployeeDataSource employeeDataSource;

    public EmployeeRepositoryImpl(EmployeeDataSource employeeDataSource) {
        this.employeeDataSource = employeeDataSource;
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return employeeDataSource.findAll().stream().map(EmployeeDTO::new).toList();
    }

    @Override
    public Either<RuntimeException, EmployeeDTO> findById(UUID id) {
        return employeeDataSource.findById(id).<Either<RuntimeException, EmployeeDTO>>map(entity -> Either.right(new EmployeeDTO(entity)))
                .orElseGet(() -> Either.left(new ObjectNotFoundException(
                        "Emprego não encontrado! Id: " + id + ", Tipo: " + EmployeeDTO.class.getName())));
    }

    @Override
    public Either<RuntimeException, EmployeeDTO> create(EmployeeDTO param) {
        return Either.right(new EmployeeDTO(employeeDataSource.save(param.toEntity())));
    }

    @Override
    public Either<RuntimeException, EmployeeDTO> update(UUID id, EmployeeDTO param) {
        throw new NotImplementedException();
    }

    @Override
    public Either<RuntimeException, EmployeeDTO> disable(UUID id) {
        final EmployeeEntity employeeEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                EmployeeDTO::toEntity);
        employeeEntity.setStatus(false);
        return Either.right(new EmployeeDTO(employeeDataSource.save(employeeEntity)));
    }

    @Override
    public Either<RuntimeException, EmployeeDTO> enable(UUID id) {
        final EmployeeEntity employeeEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                EmployeeDTO::toEntity);
        employeeEntity.setStatus(true);
        return Either.right(new EmployeeDTO(employeeDataSource.save(employeeEntity)));
    }

    @Override
    public Either<RuntimeException, Long> count() {
        return Either.right(employeeDataSource.count());
    }

}
