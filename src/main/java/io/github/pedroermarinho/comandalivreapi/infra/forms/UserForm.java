package io.github.pedroermarinho.comandalivreapi.infra.forms;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserForm {

    private String name;

    private String email;

    private String username;

    private String password;

    private String telefone;
}
