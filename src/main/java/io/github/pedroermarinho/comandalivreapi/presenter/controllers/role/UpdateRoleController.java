package io.github.pedroermarinho.comandalivreapi.presenter.controllers.role;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.RoleDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.role.UpdateRole;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.RolePathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.RoleConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.RoleForm;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + RolePathRest.ROLE_UPDATE)
public class UpdateRoleController {

    private final UpdateRole updateRole;
    private final RoleConvert convert = new RoleConvert();

    public UpdateRoleController(UpdateRole updateRole) {
        this.updateRole = updateRole;
    }

    @Operation(summary = "Atualizar cargo" )
    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable UUID id, @Valid @RequestBody RoleForm roleForm) {
        final RoleDTO role = updateRole.execute(id, convert.fromForm(roleForm));
        return ResponseEntity.ok().body(role);
    }
}
