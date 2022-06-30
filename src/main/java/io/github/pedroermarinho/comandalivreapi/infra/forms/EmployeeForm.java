package io.github.pedroermarinho.comandalivreapi.infra.forms;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

public record EmployeeForm(

        @Schema(description = "Matricula do funcionário", example = "55555")
        @Size(max = 255)
        String registration,

        @Schema(description = "Id do usuário que irá se tornar empregado", example = "ebfe4464-b19c-42b9-a62f-9fc89af29cb3", required = true)
        @NotNull
        UUID userId

) implements Serializable {

}
