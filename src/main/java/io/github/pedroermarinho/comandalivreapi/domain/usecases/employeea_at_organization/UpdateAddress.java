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
public class UpdateAddress {

    private final EmployeeAtOrganizationRepository employeeatorganizationRepository;

    public UpdateAddress(EmployeeAtOrganizationRepository employeeatorganizationRepository) {
        this.employeeatorganizationRepository = employeeatorganizationRepository;
    }

    @Transactional
    public EmployeeAtOrganizationDTO execute(UUID id, EmployeeAtOrganizationDTO employeeatorganizationParam) {

        final List<Validation<UUID>> idValidations = Arrays.asList(new NotNullValidation<>());

        idValidations.forEach(validation -> validation.validationThrow(id));

        return employeeatorganizationRepository.update(id, employeeatorganizationParam);
    }
}
