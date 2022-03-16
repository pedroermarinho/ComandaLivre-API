package io.github.pedroermarinho.comandalivreapi.infra.forms;

import lombok.Data;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@Data
public class EmployeeForm {
    
    @Schema(description = "Matricula do funcionário", example = "55555", required = false)
    @Size(max = 255)
    private String registration;

    @Schema(description = "Id do usuário que irá se tornar empregado", example = "1", required = true)
    @NotNull
    private UUID userId;
}
