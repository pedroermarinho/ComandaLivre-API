package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.record.EmployeeAtOrganizationRecord;
import io.github.pedroermarinho.comandalivreapi.domain.record.EmployeeRecord;
import io.github.pedroermarinho.comandalivreapi.domain.record.OrganizationRecord;
import io.github.pedroermarinho.comandalivreapi.domain.record.RoleRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.employee.SearchEmployee;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.organization.SearchOrganization;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.role.SearchRole;
import io.github.pedroermarinho.comandalivreapi.infra.forms.EmployeeAtOrganizationForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeAtOrganizationConvert implements Converter<EmployeeAtOrganizationForm, EmployeeAtOrganizationRecord> {

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
    public EmployeeAtOrganizationRecord convert(EmployeeAtOrganizationForm source) {
        final OrganizationRecord organizationRecord = searchOrganization.searchOrganizationById(source.organizationId());
        final EmployeeRecord employeeRecord = searchEmployee.searchEmployeeById(source.employeeId());
        final RoleRecord roleRecord = searchRole.searchRoleById(source.roleId());
        return new EmployeeAtOrganizationRecord(organizationRecord, employeeRecord, roleRecord);
    }
}
