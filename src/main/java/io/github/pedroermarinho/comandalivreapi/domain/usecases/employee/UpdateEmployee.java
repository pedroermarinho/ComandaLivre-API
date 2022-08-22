package io.github.pedroermarinho.comandalivreapi.domain.usecases.employee;

import io.github.pedroermarinho.comandalivreapi.domain.record.EmployeeRecord;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.EmployeeRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UpdateEmployee {

    private final EmployeeRepository employeeRepository;

    public UpdateEmployee(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public EmployeeRecord execute(UUID id, EmployeeRecord employeeParam) {
        UtilValidation.idNotNullValidationThrow(id);
        UtilValidation.objectNotNullValidationThrow(employeeParam);
        return employeeRepository.update(id, employeeParam).fold(
                throwable -> {
                    throw throwable;
                },
                value -> value);
    }
}
