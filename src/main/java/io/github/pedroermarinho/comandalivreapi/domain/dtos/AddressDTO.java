package io.github.pedroermarinho.comandalivreapi.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.pedroermarinho.comandalivreapi.domain.entities.AddressEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public record AddressDTO(
        UUID id,

        UUID createdById,

        UUID modifiedById,

        @JsonFormat(pattern = "dd/MM/yyyy")
        Date creationDate,

        @JsonFormat(pattern = "dd/MM/yyyy")
        Date lastModifiedDate,

        Boolean status,

        String cep,

        String logradouro,

        String bairro,

        String localidade,

        String uf,

        String number
) implements Serializable {

    public AddressDTO(String cep, String logradouro, String bairro, String localidade, String uf, String number) {
        this(
                null,
                null,
                null,
                null,
                null,
                true,
                cep,
                logradouro,
                bairro,
                localidade,
                uf,
                number
        );
    }

    public AddressDTO(AddressEntity entity) {
        this(
                entity.getId(),
                entity.getCreatedById(),
                entity.getModifiedById(),
                entity.getCreationDate(),
                entity.getLastModifiedDate(),
                entity.getStatus(),
                entity.getCep(),
                entity.getLogradouro(),
                entity.getBairro(),
                entity.getLocalidade(),
                entity.getUf(),
                entity.getNumber()
        );
    }

    public AddressEntity toEntity() {
        final AddressEntity entity = new AddressEntity();
        entity.setId(this.id);
        entity.setCreatedById(this.createdById);
        entity.setCreationDate(this.creationDate);
        entity.setModifiedById(this.modifiedById);
        entity.setLastModifiedDate(this.lastModifiedDate);
        entity.setStatus(this.status);
        entity.setCep(this.cep);
        entity.setLogradouro(this.logradouro);
        entity.setBairro(this.bairro);
        entity.setLocalidade(this.localidade);
        entity.setUf(this.uf);
        entity.setNumber(this.number);
        return entity;
    }
}
