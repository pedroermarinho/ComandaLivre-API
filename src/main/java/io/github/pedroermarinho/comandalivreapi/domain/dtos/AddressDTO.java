package io.github.pedroermarinho.comandalivreapi.domain.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddressDTO extends AuditableDTO {

    private String cep;

    private String logradouro;

    private String bairro;

    private String localidade;

    private String uf;

    private String number;

}
