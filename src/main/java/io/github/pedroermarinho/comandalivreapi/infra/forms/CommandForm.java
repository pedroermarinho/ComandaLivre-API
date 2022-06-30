package io.github.pedroermarinho.comandalivreapi.infra.forms;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public record CommandForm(

        @Schema(description = "A comada ja esta paga", example = "true")
        Boolean paidOff,

        @Schema(description = "Identificação da comanda", example = "true")
        @NotBlank
        @Size(max = 255)
        String identification
) implements Serializable {

}
