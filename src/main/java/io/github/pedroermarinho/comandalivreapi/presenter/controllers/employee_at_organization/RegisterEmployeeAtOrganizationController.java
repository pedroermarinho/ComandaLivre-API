package io.github.pedroermarinho.comandalivreapi.presenter.controllers.employee_at_organization;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeAtOrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.employeea_at_organization.RegisterEmployeeAtOrganization;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.EmployeeAtOrganizationPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.EmployeeAtOrganizationConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.EmployeeAtOrganizationForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + EmployeeAtOrganizationPathRest.EMPLOYEEATORGANIZATION_REGISTER)
public class RegisterEmployeeAtOrganizationController {

    private final RegisterEmployeeAtOrganization registerEmployeeAtOrganization;
    private final EmployeeAtOrganizationConvert convert = new EmployeeAtOrganizationConvert();

    public RegisterEmployeeAtOrganizationController(RegisterEmployeeAtOrganization registerEmployeeAtOrganization) {
        this.registerEmployeeAtOrganization = registerEmployeeAtOrganization;
    }

    @PostMapping
    public ResponseEntity<EmployeeAtOrganizationDTO> registerEmployeeAtOrganization(EmployeeAtOrganizationForm employeeatorganizationForm) {
        final EmployeeAtOrganizationDTO employeeatorganization = registerEmployeeAtOrganization.execute(convert.fromForm(employeeatorganizationForm));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employeeatorganization.getId())
                .toUri();

        return ResponseEntity.created(uri).body(employeeatorganization);
    }

}
