package io.github.pedroermarinho.comandalivreapi.domain.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeDTO extends AuditableDTO {

    private String registration;

    private UserDTO user;
}
