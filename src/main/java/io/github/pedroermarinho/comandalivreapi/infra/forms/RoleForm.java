package io.github.pedroermarinho.comandalivreapi.infra.forms;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public record RoleForm(

        @Schema(description = "Nome do cargo", example = "Gerente", required = true)
        @NotBlank
        @Size(min = 2, max = 255)
        String name,

        @Schema(
                description = "Descrição do cargo",
                example = "Gerente é responsável pelo planejamento e controle da execução dos trabalhos"
        )
        @Size(max = 255)
        String description
) implements Serializable {
}
