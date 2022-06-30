package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeAtOrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.dtos.EmployeeDTO;
import io.github.pedroermarinho.comandalivreapi.domain.dtos.OrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.dtos.RoleDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.employee.SearchEmployee;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.organization.SearchOrganization;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.role.SearchRole;
import io.github.pedroermarinho.comandalivreapi.infra.forms.EmployeeAtOrganizationForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeAtOrganizationConvert implements Converter<EmployeeAtOrganizationForm, EmployeeAtOrganizationDTO> {

    private final SearchOrganization searchOrganization;

    private final SearchEmployee searchEmployee;

    private final SearchRole searchRole;

    public EmployeeAtOrganizationConvert(
            SearchOrganization searchOrganization,
            SearchEmployee searchEmployee,
            SearchRole searchRole
    ) {
        this.searchOrganization = searchOrganization;
        this.searchEmployee = searchEmployee;
        this.searchRole = searchRole;
    }

    @Override
    public EmployeeAtOrganizationDTO convert(EmployeeAtOrganizationForm source) {
        final OrganizationDTO organizationDTO = searchOrganization.searchOrganizationById(source.organizationId());
        final EmployeeDTO employeeDTO = searchEmployee.searchEmployeeById(source.employeeId());
        final RoleDTO roleDTO = searchRole.searchRoleById(source.roleId());
        return new EmployeeAtOrganizationDTO(organizationDTO, employeeDTO, roleDTO);
    }
}
