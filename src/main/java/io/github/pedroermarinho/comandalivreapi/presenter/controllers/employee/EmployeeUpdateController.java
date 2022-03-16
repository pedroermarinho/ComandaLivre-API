package io.github.pedroermarinho.comandalivreapi.presenter.controllers.employee;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.employee.UpdateEmployee;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.EmployeePathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.EmployeeConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.EmployeeForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + EmployeePathRest.EMPLOYEE_UPDATE)
@Tag(name = "Emprego", description = "Operações de emprego")
public class EmployeeUpdateController {

    private final UpdateEmployee updateEmployee;
    private final EmployeeConvert employeeConvert;

    public EmployeeUpdateController(UpdateEmployee updateEmployee, EmployeeConvert employeeConvert) {
        this.updateEmployee = updateEmployee;
        this.employeeConvert = employeeConvert;
    }

    @Operation(summary = "Atualizar emprego")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable UUID id, @Valid @RequestBody EmployeeForm employeeForm) {
        final EmployeeDTO employee = updateEmployee.execute(id, employeeConvert.convert(employeeForm));
        return ResponseEntity.ok().body(employee);
    }
}
