package io.github.pedroermarinho.comandalivreapi.presenter.controllers.role;

import io.github.pedroermarinho.comandalivreapi.domain.record.RoleRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.role.StatusRole;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.RolePathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(value = PathRest.API + PathRest.VERSION + RolePathRest.ROLE_STATUS)
@Tag(name = "Cargo", description = "Operações de cargo")
public class RoleStatusController {

    private final StatusRole statusRole;

    public RoleStatusController(StatusRole statusRole) {
        this.statusRole = statusRole;
    }

    @Operation(summary = "Desabilitar cargo")
    @PatchMapping("/{id}")
    public ResponseEntity<RoleRecord> disableRole(@PathVariable UUID id) {
        final RoleRecord role = statusRole.disableRole(id);
        return ResponseEntity.ok().body(role);
    }
}
