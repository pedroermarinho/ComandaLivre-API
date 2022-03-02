package io.github.pedroermarinho.comandalivreapi.infra.forms;

import io.github.pedroermarinho.comandalivreapi.infra.validation.cep.CepValidationForm;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
@Data
public class AddressForm {

    @NotBlank
    @CepValidationForm
    private String cep;

    private String logradouro;

    private String bairro;

    private String localidade;

    private String uf;

    private String number;
}
