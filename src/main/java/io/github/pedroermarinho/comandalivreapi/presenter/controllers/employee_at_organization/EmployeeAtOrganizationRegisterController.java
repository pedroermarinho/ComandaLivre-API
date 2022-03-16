package io.github.pedroermarinho.comandalivreapi.presenter.controllers.employee_at_organization;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeAtOrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.employeea_at_organization.RegisterEmployeeAtOrganization;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.EmployeeAtOrganizationPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.EmployeeAtOrganizationConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.EmployeeAtOrganizationForm;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + EmployeeAtOrganizationPathRest.EMPLOYEEATORGANIZATION_REGISTER)
public class EmployeeAtOrganizationRegisterController {

    private final RegisterEmployeeAtOrganization registerEmployeeAtOrganization;
    private final EmployeeAtOrganizationConvert employeeAtOrganizationConvert;

    public EmployeeAtOrganizationRegisterController(RegisterEmployeeAtOrganization registerEmployeeAtOrganization, EmployeeAtOrganizationConvert employeeAtOrganizationConvert) {
        this.registerEmployeeAtOrganization = registerEmployeeAtOrganization;
        this.employeeAtOrganizationConvert = employeeAtOrganizationConvert;
    }

    @Operation(tags = {"Emprego", "Organização"})
    @PostMapping
    public ResponseEntity<EmployeeAtOrganizationDTO> registerEmployeeAtOrganization(EmployeeAtOrganizationForm employeeatorganizationForm) {
        final EmployeeAtOrganizationDTO employeeAtOrganizationDTO = registerEmployeeAtOrganization.execute(employeeAtOrganizationConvert.convert(employeeatorganizationForm));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employeeAtOrganizationDTO.id())
                .toUri();

        return ResponseEntity.created(uri).body(employeeAtOrganizationDTO);
    }

}
