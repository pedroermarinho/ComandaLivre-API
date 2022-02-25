package io.github.pedroermarinho.comandalivreapi.domain.usecases.employee;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.EmployeeRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class StatusAddress {

    private final EmployeeRepository employeeRepository;

    public StatusAddress(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public EmployeeDTO disableEmployee(UUID id) {
        final List<Validation<UUID>> validations = Arrays.asList(new NotNullValidation<>());

        validations.forEach(validation -> validation.validationThrow(id));

        return employeeRepository.disable(id);
    }

    @Transactional
    public EmployeeDTO enableEmployee(UUID id) {
        final List<Validation<UUID>> validations = Arrays.asList(new NotNullValidation<>());

        validations.forEach(validation -> validation.validationThrow(id));

        return employeeRepository.enable(id);
    }
}
