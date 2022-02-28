package io.github.pedroermarinho.comandalivreapi.presenter.controllers.organization;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.OrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.organization.StatusOrganization;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.OrganizationPathRest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + OrganizationPathRest.ORGANIZATION_STATUS)
public class StatusOrganizationController {

    private final StatusOrganization statusOrganization;

    public StatusOrganizationController(StatusOrganization statusOrganization) {
        this.statusOrganization = statusOrganization;
    }

    @Operation(summary = "Desativar a organização")
    @PatchMapping("/{id}")
    public ResponseEntity<OrganizationDTO> disableOrganization(@PathVariable UUID id) {
        final OrganizationDTO organization = statusOrganization.disableOrganization(id);
        return ResponseEntity.ok().body(organization);
    }
}
