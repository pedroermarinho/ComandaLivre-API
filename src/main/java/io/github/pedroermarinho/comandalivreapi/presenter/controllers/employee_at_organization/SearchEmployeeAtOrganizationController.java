package io.github.pedroermarinho.comandalivreapi.presenter.controllers.employee_at_organization;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeAtOrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.employeea_at_organization.SearchEmployeeAtOrganization;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.EmployeeAtOrganizationPathRest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + EmployeeAtOrganizationPathRest.EMPLOYEEATORGANIZATION_SEARCH)
public class SearchEmployeeAtOrganizationController {

    private final SearchEmployeeAtOrganization searchEmployeeAtOrganization;

    public SearchEmployeeAtOrganizationController(SearchEmployeeAtOrganization searchEmployeeAtOrganization) {
        this.searchEmployeeAtOrganization = searchEmployeeAtOrganization;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeAtOrganizationDTO>> searchEmployeeAtOrganizationAll() {
        final List<EmployeeAtOrganizationDTO> employeeatorganizations = searchEmployeeAtOrganization.searchEmployeeAtOrganizationAll();
        return ResponseEntity.ok().body(employeeatorganizations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeAtOrganizationDTO> searchEmployeeAtOrganizationById(@PathVariable UUID id) {
        final EmployeeAtOrganizationDTO employeeatorganization = searchEmployeeAtOrganization.searchEmployeeAtOrganizationById(id);
        return ResponseEntity.ok().body(employeeatorganization);
    }

}
