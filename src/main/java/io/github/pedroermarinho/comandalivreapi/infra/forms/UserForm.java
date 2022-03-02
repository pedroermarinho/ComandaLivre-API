package io.github.pedroermarinho.comandalivreapi.infra.forms;

import io.github.pedroermarinho.comandalivreapi.infra.validation.email.EmailUniqueValidationForm;
import io.github.pedroermarinho.comandalivreapi.infra.validation.email.EmailValidationForm;
import io.github.pedroermarinho.comandalivreapi.infra.validation.username.UsernameUniqueValidationForm;
import io.github.pedroermarinho.comandalivreapi.infra.validation.username.UsernameValidationForm;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Component
@Data
public class UserForm {

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @NotBlank
    @Size(min = 5, max = 255)
    @EmailValidationForm
    @EmailUniqueValidationForm
    private String email;

    @NotBlank
    @Size(min = 3, max = 255)
    @UsernameValidationForm
    @UsernameUniqueValidationForm
    private String username;

    @NotBlank
    @Size(min = 8, max = 255)
    private String password;

    private String telefone;
}
