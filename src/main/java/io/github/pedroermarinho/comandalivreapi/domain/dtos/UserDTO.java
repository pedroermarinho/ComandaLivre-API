package io.github.pedroermarinho.comandalivreapi.domain.dtos;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends AuditableDTO {

    private String name;

    private String email;

    private String username;

    private String password;

    private String telefone;

}
