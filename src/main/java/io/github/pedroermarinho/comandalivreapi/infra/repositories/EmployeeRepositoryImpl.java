package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.entities.EmployeeEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.record.EmployeeRecord;
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
    public List<EmployeeRecord> findAll() {
        return employeeDataSource.findAll().stream().map(EmployeeRecord::new).toList();
    }

    @Override
    public Either<RuntimeException, EmployeeRecord> findById(UUID id) {
        return employeeDataSource.findById(id).<Either<RuntimeException, EmployeeRecord>>map(entity -> Either.right(new EmployeeRecord(entity)))
                .orElseGet(() -> Either.left(new ObjectNotFoundException(
                        "Emprego não encontrado! Id: " + id + ", Tipo: " + EmployeeRecord.class.getName())));
    }

    @Override
    public Either<RuntimeException, EmployeeRecord> create(EmployeeRecord param) {
        return Either.right(new EmployeeRecord(employeeDataSource.save(param.toEntity())));
    }

    @Override
    public Either<RuntimeException, EmployeeRecord> update(UUID id, EmployeeRecord param) {
        throw new NotImplementedException();
    }

    @Override
    public Either<RuntimeException, EmployeeRecord> disable(UUID id) {
        final EmployeeEntity employeeEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                EmployeeRecord::toEntity);
        employeeEntity.setStatus(false);
        return Either.right(new EmployeeRecord(employeeDataSource.save(employeeEntity)));
    }

    @Override
    public Either<RuntimeException, EmployeeRecord> enable(UUID id) {
        final EmployeeEntity employeeEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                EmployeeRecord::toEntity);
        employeeEntity.setStatus(true);
        return Either.right(new EmployeeRecord(employeeDataSource.save(employeeEntity)));
    }

    @Override
    public Either<RuntimeException, Long> count() {
        return Either.right(employeeDataSource.count());
    }

}
