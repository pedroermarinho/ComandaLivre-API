package io.github.pedroermarinho.comandalivreapi.presenter.controllers.employee;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.employee.StatusEmployee;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.EmployeePathRest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + EmployeePathRest.EMPLOYEE_STATUS)
public class StatusEmployeeController {

    private final StatusEmployee statusEmployee;

    public StatusEmployeeController(StatusEmployee statusEmployee) {
        this.statusEmployee = statusEmployee;
    }

    @Operation(summary = "Desativar emprego")
    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDTO> disableEmployee(@PathVariable UUID id) {
        final EmployeeDTO employee = statusEmployee.disableEmployee(id);
        return ResponseEntity.ok().body(employee);
    }
}
