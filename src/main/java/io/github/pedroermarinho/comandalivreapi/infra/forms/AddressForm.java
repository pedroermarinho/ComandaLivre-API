package io.github.pedroermarinho.comandalivreapi.infra.forms;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AddressForm {

    private String cep;

    private String logradouro;

    private String bairro;

    private String localidade;

    private String uf;

    private String number;
}
