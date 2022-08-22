package io.github.pedroermarinho.comandalivreapi.presenter.controllers.employee;

import io.github.pedroermarinho.comandalivreapi.domain.record.EmployeeRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.employee.RegisterEmployee;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.EmployeePathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.EmployeeConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.EmployeeForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(value = PathRest.API + PathRest.VERSION + EmployeePathRest.EMPLOYEE_REGISTER)
@Tag(name = "Emprego", description = "Operações de emprego")
public class EmployeeRegisterController {

    private final RegisterEmployee registerEmployee;
    private final EmployeeConvert employeeConvert;

    public EmployeeRegisterController(RegisterEmployee registerEmployee, EmployeeConvert employeeConvert) {
        this.registerEmployee = registerEmployee;
        this.employeeConvert = employeeConvert;
    }

    @Operation(summary = "Cadastrar novo emprego")
    @PostMapping
    public ResponseEntity<EmployeeRecord> registerEmployee(EmployeeForm employeeForm) {
        final EmployeeRecord employee = registerEmployee.execute(employeeConvert.convert(employeeForm));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employee.id())
                .toUri();

        return ResponseEntity.created(uri).body(employee);
    }

}
