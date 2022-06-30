package io.github.pedroermarinho.comandalivreapi.infra.forms;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

public record OrganizationForm(

        @Schema(description = "Nome da organização", example = "Lanche da dona maria", required = true)
        @NotBlank
        @Size(max = 255)
        String name,

        @Schema(description = "Telefone da organização", example = "+5500000000000")
        @Size(max = 16)
        String phone,

        @Schema(description = "Endereço da organização", example = "ebfe4464-b19c-42b9-a62f-9fc89af29cb3", required = false)
        UUID addressId
) implements Serializable {

}
