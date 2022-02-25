package io.github.pedroermarinho.comandalivreapi.infra.forms;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class EmployeeAtOrganizationForm {

    private String organizationId;

    private String employeeId;

    private String roleId;
}
