package io.github.pedroermarinho.comandalivreapi.infra.forms;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Component
@Data
public class RoleForm {

    @NotBlank
    @Size(min = 2, max = 255)
    private String name;

    private String description;
}
