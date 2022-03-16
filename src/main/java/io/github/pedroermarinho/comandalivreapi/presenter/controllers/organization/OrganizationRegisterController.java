package io.github.pedroermarinho.comandalivreapi.presenter.controllers.organization;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.OrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.organization.RegisterOrganization;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.OrganizationPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.OrganizationConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.OrganizationForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + OrganizationPathRest.ORGANIZATION_REGISTER)
@Tag(name = "Organização", description = "Operações da organização")
public class OrganizationRegisterController {

    private final RegisterOrganization registerOrganization;
    private final OrganizationConvert organizationConvert;

    public OrganizationRegisterController(RegisterOrganization registerOrganization, OrganizationConvert organizationConvert) {
        this.registerOrganization = registerOrganization;
        this.organizationConvert = organizationConvert;
    }

    @Operation(summary = "Cadastrar nova organização")
    @PostMapping
    public ResponseEntity<OrganizationDTO> registerOrganization(OrganizationForm organizationForm) {
        final OrganizationDTO organization = registerOrganization.execute(organizationConvert.convert(organizationForm));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(organization.id())
                .toUri();

        return ResponseEntity.created(uri).body(organization);
    }

}
