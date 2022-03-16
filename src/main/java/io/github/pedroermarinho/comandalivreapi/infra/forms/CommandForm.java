package io.github.pedroermarinho.comandalivreapi.infra.forms;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;

@Component
@Data
public class CommandForm {

    @Schema(description = "A comada ja esta paga", example = "true", required = false)
    private Boolean paidOff;

    @Schema(description = "Identificação da comanda", example = "true", required = false)
    @NotBlank
    @Size(max = 255)
    private String identification;
}
