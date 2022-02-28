package io.github.pedroermarinho.comandalivreapi.presenter.controllers.organization;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.OrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.organization.SearchOrganization;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.OrganizationPathRest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + OrganizationPathRest.ORGANIZATION_SEARCH)
public class SearchOrganizationController {

    private final SearchOrganization searchOrganization;

    public SearchOrganizationController(SearchOrganization searchOrganization) {
        this.searchOrganization = searchOrganization;
    }

    @Operation(summary = "Listar de todas as organizações")
    @GetMapping
    public ResponseEntity<List<OrganizationDTO>> searchOrganizationAll() {
        final List<OrganizationDTO> organizations = searchOrganization.searchOrganizationAll();
        return ResponseEntity.ok().body(organizations);
    }

    @Operation(summary = "Buscar organização por id")
    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDTO> searchOrganizationById(@PathVariable UUID id) {
        final OrganizationDTO organization = searchOrganization.searchOrganizationById(id);
        return ResponseEntity.ok().body(organization);
    }



}
