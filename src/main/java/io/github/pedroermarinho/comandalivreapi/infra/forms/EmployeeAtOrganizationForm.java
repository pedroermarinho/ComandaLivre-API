package io.github.pedroermarinho.comandalivreapi.infra.forms;

import lombok.Data;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

import javax.validation.constraints.NotNull;

@Component
@Data
public class EmployeeAtOrganizationForm {

    @Schema(description = "Id da organização", example = "1", required = true)
    @NotNull
    private UUID organizationId;

    @Schema(description = "Id do empregado", example = "1", required = true)
    @NotNull
    private UUID employeeId;

    @Schema(description = "Id da cargo", example = "1", required = true)
    @NotNull
    private UUID roleId;
}
