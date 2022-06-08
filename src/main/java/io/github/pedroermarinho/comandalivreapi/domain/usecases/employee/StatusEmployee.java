package io.github.pedroermarinho.comandalivreapi.domain.usecases.employee;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeDTO;
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
    public EmployeeDTO disableEmployee(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return employeeRepository.disable(id);
    }

    @Transactional
    public EmployeeDTO enableEmployee(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return employeeRepository.enable(id);
    }
}
