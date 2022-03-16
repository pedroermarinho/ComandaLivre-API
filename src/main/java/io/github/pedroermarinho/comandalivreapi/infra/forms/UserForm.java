package io.github.pedroermarinho.comandalivreapi.infra.forms;

import io.github.pedroermarinho.comandalivreapi.infra.validation.email.EmailUniqueValidationForm;
import io.github.pedroermarinho.comandalivreapi.infra.validation.email.EmailValidationForm;
import io.github.pedroermarinho.comandalivreapi.infra.validation.username.UsernameUniqueValidationForm;
import io.github.pedroermarinho.comandalivreapi.infra.validation.username.UsernameValidationForm;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Component
@Data
public class UserForm {

    @Schema(description = "Nome do usuário", example = "Pedro marinho", required = true)
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @Schema(description = "Email do usuário", example = "exemplo@exemplo.com", required = true)
    @Email(message = "Email")
    @NotBlank
    @Size(min = 5, max = 255)
    @EmailValidationForm
    @EmailUniqueValidationForm
    private String email;

    @Schema(description = "Nome de usuário", example = "exemplo", required = true)
    @NotBlank
    @Size(min = 3, max = 255)
    @UsernameValidationForm
    @UsernameUniqueValidationForm
    private String username;

    @Schema(description = "Senha do usuário", example = "12345678", required = true)
    @NotBlank
    @Size(min = 8, max = 255)
    private String password;

    @Schema(description = "Telefone do usuário", example = "+5500000000000", required = false)
    @Size(max = 16)
    private String telefone;
}
