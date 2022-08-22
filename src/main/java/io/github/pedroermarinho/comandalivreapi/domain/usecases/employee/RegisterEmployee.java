package io.github.pedroermarinho.comandalivreapi.domain.usecases.employee;

import io.github.pedroermarinho.comandalivreapi.domain.record.EmployeeRecord;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.EmployeeRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class RegisterEmployee {

    private final EmployeeRepository employeeRepository;

    public RegisterEmployee(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public EmployeeRecord execute(EmployeeRecord employeeRegister) {
        UtilValidation.objectNotNullValidationThrow(employeeRegister);

        final List<Validation<UUID>> validations = List.of(new NotNullValidation<>());
        validations.forEach(validation -> validation.validationThrow(employeeRegister.user().id()));

        return employeeRepository.create(employeeRegister).fold(
                throwable -> {
                    throw throwable;
                },
                value -> value);
    }

}
