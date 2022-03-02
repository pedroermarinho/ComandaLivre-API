package io.github.pedroermarinho.comandalivreapi.presenter.controllers.employee;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.employee.RegisterEmployee;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.EmployeePathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.EmployeeConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.EmployeeForm;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + EmployeePathRest.EMPLOYEE_REGISTER)
public class RegisterEmployeeController {

    private final RegisterEmployee registerEmployee;
    private final EmployeeConvert convert = new EmployeeConvert();

    public RegisterEmployeeController(RegisterEmployee registerEmployee) {
        this.registerEmployee = registerEmployee;
    }

    @Operation(summary = "Cadastrar novo emprego")
    @PostMapping
    public ResponseEntity<EmployeeDTO> registerEmployee(EmployeeForm employeeForm) {
        final EmployeeDTO employee = registerEmployee.execute(convert.fromForm(employeeForm));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employee.getId())
                .toUri();

        return ResponseEntity.created(uri).body(employee);
    }

}
