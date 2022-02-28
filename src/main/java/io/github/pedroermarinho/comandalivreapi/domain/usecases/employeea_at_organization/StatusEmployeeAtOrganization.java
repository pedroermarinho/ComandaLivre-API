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
public class StatusEmployeeAtOrganization {

    private final EmployeeAtOrganizationRepository employeeAtOrganizationRepository;

    public StatusEmployeeAtOrganization(EmployeeAtOrganizationRepository employeeAtOrganizationRepository) {
        this.employeeAtOrganizationRepository = employeeAtOrganizationRepository;
    }

    @Transactional
    public EmployeeAtOrganizationDTO disableEmployeeAtOrganization(UUID id) {
        final List<Validation<UUID>> validations = Arrays.asList(new NotNullValidation<>());

        validations.forEach(validation -> validation.validationThrow(id));

        return employeeAtOrganizationRepository.disable(id);
    }

    @Transactional
    public EmployeeAtOrganizationDTO enableEmployeeAtOrganization(UUID id) {
        final List<Validation<UUID>> validations = Arrays.asList(new NotNullValidation<>());

        validations.forEach(validation -> validation.validationThrow(id));

        return employeeAtOrganizationRepository.enable(id);
    }
}
