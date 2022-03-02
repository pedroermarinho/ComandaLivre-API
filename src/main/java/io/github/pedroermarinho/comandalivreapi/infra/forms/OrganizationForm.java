package io.github.pedroermarinho.comandalivreapi.infra.forms;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
@Data
public class OrganizationForm {

    @NotBlank
    private String name;

    private String telefone;
}
