package io.github.pedroermarinho.comandalivreapi.presenter.controllers.employee_at_organization;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeAtOrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.employeea_at_organization.SearchEmployeeAtOrganization;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.EmployeeAtOrganizationPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + EmployeeAtOrganizationPathRest.EMPLOYEEATORGANIZATION_SEARCH)
public class EmployeeAtOrganizationSearchController {

    private final SearchEmployeeAtOrganization searchEmployeeAtOrganization;

    public EmployeeAtOrganizationSearchController(SearchEmployeeAtOrganization searchEmployeeAtOrganization) {
        this.searchEmployeeAtOrganization = searchEmployeeAtOrganization;
    }

    @Operation(tags = {"Emprego", "Organização"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = EmployeeAtOrganizationDTO.class)))
            )
    })
    @GetMapping
    public ResponseEntity<List<EmployeeAtOrganizationDTO>> searchEmployeeAtOrganizationAll() {
        final List<EmployeeAtOrganizationDTO> employeeAtOrganizationDTOList = searchEmployeeAtOrganization.searchEmployeeAtOrganizationAll();
        return ResponseEntity.ok().body(employeeAtOrganizationDTOList);
    }


    @Operation(tags = {"Emprego", "Organização"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(schema = @Schema(implementation = EmployeeAtOrganizationDTO.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeAtOrganizationDTO> searchEmployeeAtOrganizationById(@PathVariable UUID id) {
        final EmployeeAtOrganizationDTO employeeAtOrganizationDTO = searchEmployeeAtOrganization.searchEmployeeAtOrganizationById(id);
        return ResponseEntity.ok().body(employeeAtOrganizationDTO);
    }

}
