package io.github.pedroermarinho.comandalivreapi.presenter.controllers.employee_at_organization;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeAtOrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.employeea_at_organization.UpdateEmployeeAtOrganization;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.EmployeeAtOrganizationPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.EmployeeAtOrganizationConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.EmployeeAtOrganizationForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + EmployeeAtOrganizationPathRest.EMPLOYEEATORGANIZATION_UPDATE)
public class UpdateEmployeeAtOrganizationController {

    private final UpdateEmployeeAtOrganization updateEmployeeAtOrganization;
    private final EmployeeAtOrganizationConvert convert = new EmployeeAtOrganizationConvert(searchOrganization, searchEmployee, searchRole);

    public UpdateEmployeeAtOrganizationController(UpdateEmployeeAtOrganization updateEmployeeAtOrganization) {
        this.updateEmployeeAtOrganization = updateEmployeeAtOrganization;
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeAtOrganizationDTO> disableEmployeeAtOrganization(@PathVariable UUID id, @Valid @RequestBody EmployeeAtOrganizationForm employeeatorganizationForm) {
        final EmployeeAtOrganizationDTO employeeatorganization = updateEmployeeAtOrganization.execute(id, convert.fromForm(employeeatorganizationForm));
        return ResponseEntity.ok().body(employeeatorganization);
    }
}
