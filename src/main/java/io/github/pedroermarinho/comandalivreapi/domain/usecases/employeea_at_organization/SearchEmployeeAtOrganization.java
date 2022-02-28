package io.github.pedroermarinho.comandalivreapi.domain.usecases.employeea_at_organization;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeAtOrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.EmployeeAtOrganizationRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class SearchEmployeeAtOrganization {

    private final EmployeeAtOrganizationRepository employeeAtOrganizationRepository;

    public SearchEmployeeAtOrganization(EmployeeAtOrganizationRepository employeeAtOrganizationRepository) {
        this.employeeAtOrganizationRepository = employeeAtOrganizationRepository;
    }

    public EmployeeAtOrganizationDTO searchEmployeeAtOrganizationById(UUID id) {
        final List<Validation<UUID>> validations = Arrays.asList(new NotNullValidation<>());

        validations.forEach(validation -> validation.validationThrow(id));

        return employeeAtOrganizationRepository.findById(id);
    }


    public List<EmployeeAtOrganizationDTO> searchEmployeeAtOrganizationAll() {
        return employeeAtOrganizationRepository.findAll();
    }

}
