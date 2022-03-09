package io.github.pedroermarinho.comandalivreapi.infra.forms;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
public class EmployeeAtOrganizationForm {

    private UUID organizationId;

    private UUID employeeId;

    private UUID roleId;
}
