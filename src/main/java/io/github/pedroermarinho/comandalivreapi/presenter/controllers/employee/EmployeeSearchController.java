package io.github.pedroermarinho.comandalivreapi.presenter.controllers.employee;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.employee.SearchEmployee;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.EmployeePathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + EmployeePathRest.EMPLOYEE_SEARCH)
@Tag(name = "Emprego", description = "Operações de emprego")
public class EmployeeSearchController {

    private final SearchEmployee searchEmployee;

    public EmployeeSearchController(SearchEmployee searchEmployee) {
        this.searchEmployee = searchEmployee;
    }

    @Operation(summary = "Lista de empregos")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = EmployeeDTO.class)))
            )
    })
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> searchEmployeeAll() {
        final List<EmployeeDTO> employees = searchEmployee.searchEmployeeAll();
        return ResponseEntity.ok().body(employees);
    }

    @Operation(summary = "Buscar emprego por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Contact não encontrado"),
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(schema = @Schema(implementation = EmployeeDTO.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> searchEmployeeById(@PathVariable UUID id) {
        final EmployeeDTO employee = searchEmployee.searchEmployeeById(id);
        return ResponseEntity.ok().body(employee);
    }


}
