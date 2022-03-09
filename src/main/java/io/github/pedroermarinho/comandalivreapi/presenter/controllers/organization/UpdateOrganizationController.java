package io.github.pedroermarinho.comandalivreapi.presenter.controllers.organization;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.OrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.organization.UpdateOrganization;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.OrganizationPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.OrganizationConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.OrganizationForm;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + OrganizationPathRest.ORGANIZATION_UPDATE)
public class UpdateOrganizationController {

    private final UpdateOrganization updateOrganization;
    private final OrganizationConvert convert = new OrganizationConvert(searchAddress);

    public UpdateOrganizationController(UpdateOrganization updateOrganization) {
        this.updateOrganization = updateOrganization;
    }

    @Operation(summary = "Atualizar a organização")
    @PutMapping("/{id}")
    public ResponseEntity<OrganizationDTO> updateOrganization(@PathVariable UUID id, @Valid @RequestBody OrganizationForm organizationForm) {
        final OrganizationDTO organization = updateOrganization.execute(id, convert.fromForm(organizationForm));
        return ResponseEntity.ok().body(organization);
    }
}
