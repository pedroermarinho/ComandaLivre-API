package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.EmployeeEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.EmployeeRepository;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.EmployeeDataSource;
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
    public EmployeeDTO findById(UUID id) {
        return new EmployeeDTO(employeeDataSource.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Emprego não encontrado! Id: " + id + ", Tipo: " + EmployeeDTO.class.getName())));
    }

    @Override
    public EmployeeDTO create(EmployeeDTO param) {
        return new EmployeeDTO(employeeDataSource.save(param.toEntity()));
    }

    @Override
    public EmployeeDTO update(UUID id, EmployeeDTO param) {
        throw new NotImplementedException();
    }

    @Override
    public EmployeeDTO disable(UUID id) {
        final EmployeeEntity employeeEntity = findById(id).toEntity();
        employeeEntity.setStatus(false);
        return new EmployeeDTO(employeeDataSource.save(employeeEntity));
    }

    @Override
    public EmployeeDTO enable(UUID id) {
        final EmployeeEntity employeeEntity = findById(id).toEntity();
        employeeEntity.setStatus(true);
        return new EmployeeDTO(employeeDataSource.save(employeeEntity));
    }

    @Override
    public long count() {
        return employeeDataSource.count();
    }

}
