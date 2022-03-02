package io.github.pedroermarinho.comandalivreapi.presenter.controllers.employee_at_organization;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeAtOrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.employeea_at_organization.StatusEmployeeAtOrganization;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.EmployeeAtOrganizationPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + EmployeeAtOrganizationPathRest.EMPLOYEEATORGANIZATION_STATUS)
public class StatusEmployeeAtOrganizationController {

    private final StatusEmployeeAtOrganization statusEmployeeAtOrganization;

    public StatusEmployeeAtOrganizationController(StatusEmployeeAtOrganization statusEmployeeAtOrganization) {
        this.statusEmployeeAtOrganization = statusEmployeeAtOrganization;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeAtOrganizationDTO> disableEmployeeAtOrganization(@PathVariable UUID id) {
        final EmployeeAtOrganizationDTO employeeatorganization = statusEmployeeAtOrganization.disableEmployeeAtOrganization(id);
        return ResponseEntity.ok().body(employeeatorganization);
    }
}
