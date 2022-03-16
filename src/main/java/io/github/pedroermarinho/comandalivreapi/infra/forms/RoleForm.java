package io.github.pedroermarinho.comandalivreapi.infra.forms;

import lombok.Data;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Component
@Data
public class RoleForm {

    @Schema(description = "Nome do cargo", example = "Gerente", required = true)
    @NotBlank
    @Size(min = 2, max = 255)
    private String name;

    @Schema(
        description = "Descrição do cargo", 
        example = "Gerente é responsável pelo planejamento e controle da execução dos trabalhos", 
        required = false
        )
    @Size(max = 255)
    private String description;
}
