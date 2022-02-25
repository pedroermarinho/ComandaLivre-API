package io.github.pedroermarinho.comandalivreapi.domain.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends AuditableDTO {

    private String name;

    private String description;

}
