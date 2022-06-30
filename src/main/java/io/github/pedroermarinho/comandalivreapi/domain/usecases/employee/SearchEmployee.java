package io.github.pedroermarinho.comandalivreapi.domain.usecases.employee;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.EmployeeRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SearchEmployee {

    private final EmployeeRepository employeeRepository;

    public SearchEmployee(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO searchEmployeeById(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return employeeRepository.findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                value -> value);
    }


    public List<EmployeeDTO> searchEmployeeAll() {
        return employeeRepository.findAll();
    }

}
