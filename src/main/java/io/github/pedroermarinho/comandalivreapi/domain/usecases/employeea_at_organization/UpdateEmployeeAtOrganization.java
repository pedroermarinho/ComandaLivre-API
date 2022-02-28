package io.github.pedroermarinho.comandalivreapi.domain.usecases.employeea_at_organization;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeAtOrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.EmployeeAtOrganizationRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UpdateEmployeeAtOrganization {

    private final EmployeeAtOrganizationRepository employeeAtOrganizationRepository;

    public UpdateEmployeeAtOrganization(EmployeeAtOrganizationRepository employeeAtOrganizationRepository) {
        this.employeeAtOrganizationRepository = employeeAtOrganizationRepository;
    }

    @Transactional
    public EmployeeAtOrganizationDTO execute(UUID id, EmployeeAtOrganizationDTO employeeAtOrganizationParam) {

        final List<Validation<UUID>> idValidations = Arrays.asList(new NotNullValidation<>());

        idValidations.forEach(validation -> validation.validationThrow(id));

        return employeeAtOrganizationRepository.update(id, employeeAtOrganizationParam);
    }
}
