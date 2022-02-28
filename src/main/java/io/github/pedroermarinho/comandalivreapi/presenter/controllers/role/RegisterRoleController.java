package io.github.pedroermarinho.comandalivreapi.presenter.controllers.role;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.RoleDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.role.RegisterRole;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.RolePathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.RoleConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.RoleForm;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + RolePathRest.ROLE_REGISTER)
public class RegisterRoleController {

    private final RegisterRole registerRole;
    private final RoleConvert convert = new RoleConvert();

    public RegisterRoleController(RegisterRole registerRole) {
        this.registerRole = registerRole;
    }

    @Operation(summary = "Cadastrar novo cargo")
    @PostMapping
    public ResponseEntity<RoleDTO> registerRole(RoleForm roleForm) {
        final RoleDTO role = registerRole.execute(convert.fromForm(roleForm));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(role.getId())
                .toUri();

        return ResponseEntity.created(uri).body(role);
    }

}
