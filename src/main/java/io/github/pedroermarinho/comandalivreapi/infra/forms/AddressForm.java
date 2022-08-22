package io.github.pedroermarinho.comandalivreapi.infra.forms;

import io.github.pedroermarinho.comandalivreapi.infra.validation.cep.CepValidationForm;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public record AddressForm(
        @Schema(description = "Cep do endereço.", example = "00000000", required = true)
        @NotBlank
        @CepValidationForm
        @Size(max = 8)
        String cep,

        @Size(max = 255)
        String logradouro,

        @Schema(description = "Bairro do endereço", example = "Fatima", required = false)
        @Size(max = 255)
        String bairro,

        @Size(max = 255)
        String localidade,

        @Schema(description = "Sigla do estado", example = "AM", required = false)
        @Size(max = 2)
        String uf,

        @Schema(description = "Numero do endereço", example = "111", required = false)
        @Size(max = 255)
        String number
) implements Serializable {

}
