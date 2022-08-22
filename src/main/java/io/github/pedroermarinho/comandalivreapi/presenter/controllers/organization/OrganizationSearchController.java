package io.github.pedroermarinho.comandalivreapi.presenter.controllers.organization;

import io.github.pedroermarinho.comandalivreapi.domain.record.OrganizationRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.organization.SearchOrganization;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.OrganizationPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "Bearer Authentication")
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
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrganizationRecord.class)))
            )
    })
    @GetMapping
    public ResponseEntity<List<OrganizationRecord>> searchOrganizationAll() {
        final List<OrganizationRecord> organizations = searchOrganization.searchOrganizationAll();
        return ResponseEntity.ok().body(organizations);
    }

    @Operation(summary = "Buscar organização por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Organização não encontrado"),
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(schema = @Schema(implementation = OrganizationRecord.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrganizationRecord> searchOrganizationById(@PathVariable UUID id) {
        final OrganizationRecord organization = searchOrganization.searchOrganizationById(id);
        return ResponseEntity.ok().body(organization);
    }


}
