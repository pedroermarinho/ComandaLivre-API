package io.github.pedroermarinho.comandalivreapi.domain.usecases.employee;

import io.github.pedroermarinho.comandalivreapi.domain.record.EmployeeRecord;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.EmployeeRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class StatusEmployee {

    private final EmployeeRepository employeeRepository;

    public StatusEmployee(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public EmployeeRecord disableEmployee(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return employeeRepository.disable(id).fold(
                throwable -> {
                    throw throwable;
                },
                value -> value);
    }

    @Transactional
    public EmployeeRecord enableEmployee(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return employeeRepository.enable(id).fold(
                throwable -> {
                    throw throwable;
                },
                value -> value);
    }
}
