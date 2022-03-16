package io.github.pedroermarinho.comandalivreapi.presenter.controllers.organization;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.OrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.organization.SearchOrganization;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.OrganizationPathRest;
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
@RequestMapping(value = PathRest.API + PathRest.VERSION + OrganizationPathRest.ORGANIZATION_SEARCH)
@Tag(name = "Organização", description = "Operações da organização")
public class OrganizationSearchController {

    private final SearchOrganization searchOrganization;

    public OrganizationSearchController(SearchOrganization searchOrganization) {
        this.searchOrganization = searchOrganization;
    }

    @Operation(summary = "Listar de todas as organizações")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrganizationDTO.class)))
            )
    })
    @GetMapping
    public ResponseEntity<List<OrganizationDTO>> searchOrganizationAll() {
        final List<OrganizationDTO> organizations = searchOrganization.searchOrganizationAll();
        return ResponseEntity.ok().body(organizations);
    }

    @Operation(summary = "Buscar organização por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Organização não encontrado"),
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(schema = @Schema(implementation = OrganizationDTO.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDTO> searchOrganizationById(@PathVariable UUID id) {
        final OrganizationDTO organization = searchOrganization.searchOrganizationById(id);
        return ResponseEntity.ok().body(organization);
    }


}
