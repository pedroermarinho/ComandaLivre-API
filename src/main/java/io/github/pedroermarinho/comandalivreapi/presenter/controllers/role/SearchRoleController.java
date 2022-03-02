package io.github.pedroermarinho.comandalivreapi.presenter.controllers.role;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.RoleDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.role.SearchRole;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.RolePathRest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + RolePathRest.ROLE_SEARCH)
public class SearchRoleController {

    private final SearchRole searchRole;

    public SearchRoleController(SearchRole searchRole) {
        this.searchRole = searchRole;
    }

    @Operation(summary = "Lista de cargos")
    @GetMapping
    public ResponseEntity<List<RoleDTO>> searchRoleAll() {
        final List<RoleDTO> roles = searchRole.searchRoleAll();
        return ResponseEntity.ok().body(roles);
    }

    @Operation(summary = "Buscar cargo por id")
    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> searchRoleById(@PathVariable UUID id) {
        final RoleDTO role = searchRole.searchRoleById(id);
        return ResponseEntity.ok().body(role);
    }

}
