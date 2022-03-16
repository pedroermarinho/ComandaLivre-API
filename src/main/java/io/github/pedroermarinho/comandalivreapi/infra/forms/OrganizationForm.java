package io.github.pedroermarinho.comandalivreapi.infra.forms;

import lombok.Data;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.UUID;

@Component
@Data
public class OrganizationForm {

    @Schema(description = "Nome da organização", example = "Lanche da dona maria", required = true)
    @NotBlank
    @Size(max = 255)
    private String name;

    @Schema(description = "Telefone da organização", example = "+5500000000000", required = false)
    @Size(max = 16)
    private String telefone;

    @Schema(description = "Endereço da organização", example = "1", required = false)
    private UUID addressId;
}
