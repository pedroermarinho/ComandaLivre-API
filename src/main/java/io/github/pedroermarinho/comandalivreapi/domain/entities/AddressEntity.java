package io.github.pedroermarinho.comandalivreapi.domain.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity
@Table(name = "addresses")
@DynamicUpdate
@DynamicInsert
@Data
public class AddressEntity extends Auditable{
    
    private String cep;

    private String logradouro;

    private String bairro;

    private String localidade;

    private String uf;

    private String number;

    public AddressEntity() {
    }

    public AddressEntity(String cep, String logradouro, String bairro, String localidade, String uf) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }


}
