package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.entities.EmployeeAtOrganizationEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.record.EmployeeAtOrganizationRecord;
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
    public List<EmployeeAtOrganizationRecord> findAll() {
        return employeeAtOrganizationDataSource.findAll().stream().map(EmployeeAtOrganizationRecord::new).toList();
    }

    @Override
    public Either<RuntimeException, EmployeeAtOrganizationRecord> findById(UUID id) {
        return employeeAtOrganizationDataSource.findById(id).<Either<RuntimeException, EmployeeAtOrganizationRecord>>map(entity -> Either.right(new EmployeeAtOrganizationRecord(entity)))
                .orElseGet(() -> Either.left(new ObjectNotFoundException(
                        "Emprego na orginação não encontrado! Id: " + id + ", Tipo: "
                                + EmployeeAtOrganizationRecord.class.getName())));
    }

    @Override
    public Either<RuntimeException, EmployeeAtOrganizationRecord> create(EmployeeAtOrganizationRecord param) {
        return Either.right(new EmployeeAtOrganizationRecord(employeeAtOrganizationDataSource.save(param.toEntity())));
    }

    @Override
    public Either<RuntimeException, EmployeeAtOrganizationRecord> update(UUID id, EmployeeAtOrganizationRecord param) {
        throw new NotImplementedException();
    }

    @Override
    public Either<RuntimeException, EmployeeAtOrganizationRecord> disable(UUID id) {
        final EmployeeAtOrganizationEntity employeeAtOrganizationEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                EmployeeAtOrganizationRecord::toEntity);
        employeeAtOrganizationEntity.setStatus(false);
        return Either.right(new EmployeeAtOrganizationRecord(employeeAtOrganizationDataSource.save(employeeAtOrganizationEntity)));
    }

    @Override
    public Either<RuntimeException, EmployeeAtOrganizationRecord> enable(UUID id) {
        final EmployeeAtOrganizationEntity employeeAtOrganizationEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                EmployeeAtOrganizationRecord::toEntity);
        employeeAtOrganizationEntity.setStatus(true);
        return Either.right(new EmployeeAtOrganizationRecord(employeeAtOrganizationDataSource.save(employeeAtOrganizationEntity)));
    }

    @Override
    public Either<RuntimeException, Long> count() {
        return Either.right(employeeAtOrganizationDataSource.count());
    }

}
