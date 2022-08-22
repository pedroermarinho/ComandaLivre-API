package io.github.pedroermarinho.comandalivreapi.presenter.controllers.employee;

import io.github.pedroermarinho.comandalivreapi.domain.record.EmployeeRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.employee.StatusEmployee;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.EmployeePathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(value = PathRest.API + PathRest.VERSION + EmployeePathRest.EMPLOYEE_STATUS)
@Tag(name = "Emprego", description = "Operações de emprego")
public class EmployeeStatusController {

    private final StatusEmployee statusEmployee;

    public EmployeeStatusController(StatusEmployee statusEmployee) {
        this.statusEmployee = statusEmployee;
    }

    @Operation(summary = "Desativar emprego")
    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeRecord> disableEmployee(@PathVariable UUID id) {
        final EmployeeRecord employee = statusEmployee.disableEmployee(id);
        return ResponseEntity.ok().body(employee);
    }
}
