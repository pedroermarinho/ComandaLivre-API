package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeAtOrganizationDTO;
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
    private final EmployeeAtOrganizationConvert convert = new EmployeeAtOrganizationConvert();

    public EmployeeAtOrganizationRepositoryImpl(EmployeeAtOrganizationDataSource employeeAtOrganizationDataSource) {
        this.employeeAtOrganizationDataSource = employeeAtOrganizationDataSource;
    }

    @Override
    public List<EmployeeAtOrganizationDTO> findAll() {
        return convert.formEntity(employeeAtOrganizationDataSource.findAll());
    }

    @Override
    public EmployeeAtOrganizationDTO findById(UUID id) {
        return convert.formEntity(employeeAtOrganizationDataSource.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Emprego na orginação não encontrado! Id: " + id + ", Tipo: "
                                + EmployeeAtOrganizationDTO.class.getName())));
    }

    @Override
    public EmployeeAtOrganizationDTO create(EmployeeAtOrganizationDTO param) {
        return convert.formEntity(employeeAtOrganizationDataSource.save(convert.formDTO(param)));
    }

    @Override
    public EmployeeAtOrganizationDTO update(UUID id, EmployeeAtOrganizationDTO param) {
        throw new NotImplementedException();
    }

    @Override
    public EmployeeAtOrganizationDTO disable(UUID id) {
        final EmployeeAtOrganizationDTO employeeAtOrganizationDTO = findById(id);
        employeeAtOrganizationDTO.setStatus(false);
        return convert.formEntity(employeeAtOrganizationDataSource.save(convert.formDTO(employeeAtOrganizationDTO)));
    }

    @Override
    public EmployeeAtOrganizationDTO enable(UUID id) {
        final EmployeeAtOrganizationDTO employeeAtOrganizationDTO = findById(id);
        employeeAtOrganizationDTO.setStatus(true);
        return convert.formEntity(employeeAtOrganizationDataSource.save(convert.formDTO(employeeAtOrganizationDTO)));
    }

}
