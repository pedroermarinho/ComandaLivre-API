package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeDTO;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.EmployeeRepository;
import io.github.pedroermarinho.comandalivreapi.infra.convert.EmployeeConvert;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.EmployeeDataSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EmployeeDataSource employeeDataSource;
    private final EmployeeConvert convert = new EmployeeConvert();

    public EmployeeRepositoryImpl(EmployeeDataSource employeeDataSource) {
        this.employeeDataSource = employeeDataSource;
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return convert.formEntity(employeeDataSource.findAll());
    }

    @Override
    public EmployeeDTO findById(UUID id) {
        return convert.formEntity(employeeDataSource.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Emprego não encontrado! Id: " + id + ", Tipo: " + EmployeeDTO.class.getName())));
    }

    @Override
    public EmployeeDTO create(EmployeeDTO param) {
        return convert.formEntity(employeeDataSource.save(convert.formDTO(param)));
    }

    @Override
    public EmployeeDTO update(UUID id, EmployeeDTO param) {
        throw new NotImplementedException();
    }

    @Override
    public EmployeeDTO disable(UUID id) {
        final EmployeeDTO employeeDTO = findById(id);
        employeeDTO.setStatus(false);
        return convert.formEntity(employeeDataSource.save(convert.formDTO(employeeDTO)));
    }

    @Override
    public EmployeeDTO enable(UUID id) {
        final EmployeeDTO employeeDTO = findById(id);
        employeeDTO.setStatus(true);
        return convert.formEntity(employeeDataSource.save(convert.formDTO(employeeDTO)));
    }

}
