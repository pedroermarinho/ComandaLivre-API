package io.github.pedroermarinho.comandalivreapi.presenter.controllers.role;

import io.github.pedroermarinho.comandalivreapi.domain.record.RoleRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.role.RegisterRole;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.RolePathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.RoleConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.RoleForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(value = PathRest.API + PathRest.VERSION + RolePathRest.ROLE_REGISTER)
@Tag(name = "Cargo", description = "Operações de cargo")
public class RoleRegisterController {

    private final RegisterRole registerRole;
    private final RoleConvert roleConvert;

    public RoleRegisterController(RegisterRole registerRole, RoleConvert roleConvert) {
        this.registerRole = registerRole;
        this.roleConvert = roleConvert;
    }

    @Operation(summary = "Cadastrar novo cargo")
    @PostMapping
    public ResponseEntity<RoleRecord> registerRole(RoleForm roleForm) {
        System.out.println(roleForm);
        final RoleRecord role = registerRole.execute(roleConvert.convert(roleForm));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(role.id())
                .toUri();

        return ResponseEntity.created(uri).body(role);
    }

}
