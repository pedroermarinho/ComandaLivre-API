package io.github.pedroermarinho.comandalivreapi.presenter.controllers.role;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.RoleDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.role.StatusRole;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.RolePathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + RolePathRest.ROLE_STATUS)
@Tag(name = "Cargo", description = "Operações de cargo")
public class RoleStatusController {

    private final StatusRole statusRole;

    public RoleStatusController(StatusRole statusRole) {
        this.statusRole = statusRole;
    }

    @Operation(summary = "Desabilitar cargo")
    @PatchMapping("/{id}")
    public ResponseEntity<RoleDTO> disableRole(@PathVariable UUID id) {
        final RoleDTO role = statusRole.disableRole(id);
        return ResponseEntity.ok().body(role);
    }
}
