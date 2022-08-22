package io.github.pedroermarinho.comandalivreapi.presenter.controllers.role;

import io.github.pedroermarinho.comandalivreapi.domain.record.RoleRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.role.SearchRole;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.RolePathRest;
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
@RequestMapping(value = PathRest.API + PathRest.VERSION + RolePathRest.ROLE_SEARCH)
@Tag(name = "Cargo", description = "Operações de cargo")
public class RoleSearchController {

    private final SearchRole searchRole;

    public RoleSearchController(SearchRole searchRole) {
        this.searchRole = searchRole;
    }

    @Operation(summary = "Lista de cargos")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = RoleRecord.class))
                    )
            )
    })
    @GetMapping
    public ResponseEntity<List<RoleRecord>> searchRoleAll() {
        final List<RoleRecord> roles = searchRole.searchRoleAll();
        return ResponseEntity.ok().body(roles);
    }

    @Operation(summary = "Buscar cargo por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Cargo não encontrado"),
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(schema = @Schema(implementation = RoleRecord.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<RoleRecord> searchRoleById(@PathVariable UUID id) {
        final RoleRecord role = searchRole.searchRoleById(id);
        return ResponseEntity.ok().body(role);
    }

}
