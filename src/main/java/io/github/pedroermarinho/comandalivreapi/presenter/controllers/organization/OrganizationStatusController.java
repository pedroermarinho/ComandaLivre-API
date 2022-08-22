package io.github.pedroermarinho.comandalivreapi.presenter.controllers.organization;

import io.github.pedroermarinho.comandalivreapi.domain.record.OrganizationRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.organization.StatusOrganization;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.OrganizationPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(value = PathRest.API + PathRest.VERSION + OrganizationPathRest.ORGANIZATION_STATUS)
@Tag(name = "Organização", description = "Operações da organização")
public class OrganizationStatusController {

    private final StatusOrganization statusOrganization;

    public OrganizationStatusController(StatusOrganization statusOrganization) {
        this.statusOrganization = statusOrganization;
    }

    @Operation(summary = "Desativar a organização")
    @PatchMapping("/{id}")
    public ResponseEntity<OrganizationRecord> disableOrganization(@PathVariable UUID id) {
        final OrganizationRecord organization = statusOrganization.disableOrganization(id);
        return ResponseEntity.ok().body(organization);
    }
}
