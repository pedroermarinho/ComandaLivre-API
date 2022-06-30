package io.github.pedroermarinho.comandalivreapi.infra.forms;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public record EmployeeAtOrganizationForm(

        @Schema(description = "Id da organização", example = "ebfe4464-b19c-42b9-a62f-9fc89af29cb3", required = true)
        @NotNull
        UUID organizationId,

        @Schema(description = "Id do empregado", example = "ebfe4464-b19c-42b9-a62f-9fc89af29cb3", required = true)
        @NotNull
        UUID employeeId,

        @Schema(description = "Id da cargo", example = "ebfe4464-b19c-42b9-a62f-9fc89af29cb3", required = true)
        @NotNull
        UUID roleId
) implements Serializable {
}
