package io.github.pedroermarinho.comandalivreapi.presenter.controllers.employee;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.employee.UpdateEmployee;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.EmployeePathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.EmployeeConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.EmployeeForm;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + EmployeePathRest.EMPLOYEE_UPDATE)
public class UpdateEmployeeController {

    private final UpdateEmployee updateEmployee;
    private final EmployeeConvert convert = new EmployeeConvert();

    public UpdateEmployeeController(UpdateEmployee updateEmployee) {
        this.updateEmployee = updateEmployee;
    }
    
    @Operation(summary = "Atualizar emprego")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable UUID id, @Valid @RequestBody EmployeeForm employeeForm) {
        final EmployeeDTO employee = updateEmployee.execute(id, convert.fromForm(employeeForm));
        return ResponseEntity.ok().body(employee);
    }
}
