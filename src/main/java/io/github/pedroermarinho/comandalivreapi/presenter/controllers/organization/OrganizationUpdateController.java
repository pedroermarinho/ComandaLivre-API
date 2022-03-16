package io.github.pedroermarinho.comandalivreapi.presenter.controllers.organization;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.OrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.organization.UpdateOrganization;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.OrganizationPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.OrganizationConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.OrganizationForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + OrganizationPathRest.ORGANIZATION_UPDATE)
@Tag(name = "Organização", description = "Operações da organização")
public class OrganizationUpdateController {

    private final UpdateOrganization updateOrganization;
    private final OrganizationConvert organizationConvert;

    public OrganizationUpdateController(UpdateOrganization updateOrganization, OrganizationConvert organizationConvert) {
        this.updateOrganization = updateOrganization;
        this.organizationConvert = organizationConvert;
    }

    @Operation(summary = "Atualizar a organização")
    @PutMapping("/{id}")
    public ResponseEntity<OrganizationDTO> updateOrganization(@PathVariable UUID id, @Valid @RequestBody OrganizationForm organizationForm) {
        final OrganizationDTO organization = updateOrganization.execute(id, organizationConvert.convert(organizationForm));
        return ResponseEntity.ok().body(organization);
    }
}
