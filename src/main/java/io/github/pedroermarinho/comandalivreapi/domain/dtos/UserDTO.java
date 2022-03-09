package io.github.pedroermarinho.comandalivreapi.domain.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.pedroermarinho.comandalivreapi.domain.entities.UserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


public record UserDTO(
        UUID id,

        UUID createdById,

        UUID modifiedById,

        @JsonFormat(pattern = "dd/MM/yyyy")
        Date creationDate,

        @JsonFormat(pattern = "dd/MM/yyyy")
        Date lastModifiedDate,

        Boolean status,

        String name,

        String email,

        String username,

        String password,

        String telefone

) implements Serializable {

        public UserDTO(String name, String email, String username, String password, String telefone) {
                this(
                        null,
                        null,
                        null,
                        null,
                        null,
                        true,
                        name,
                        email,
                        username,
                        password,
                        telefone
                );
        }

        public UserDTO(UserEntity entity) {
                this(
                        entity.getId(),
                        entity.getCreatedById(),
                        entity.getModifiedById(),
                        entity.getCreationDate(),
                        entity.getLastModifiedDate(),
                        entity.getStatus(),
                        entity.getName(),
                        entity.getEmail(),
                        entity.getUsername(),
                        entity.getPassword(),
                        entity.getTelefone()
                );
        }

        public UserEntity toEntity() {
                final UserEntity entity = new UserEntity();
                entity.setId(this.id);
                entity.setCreatedById(this.createdById);
                entity.setCreationDate(this.creationDate);
                entity.setModifiedById(this.modifiedById);
                entity.setLastModifiedDate(this.lastModifiedDate);
                entity.setStatus(this.status);
                entity.setName(this.name);
                entity.setEmail(this.email);
                entity.setUsername(this.username);
                entity.setPassword(this.password);
                entity.setTelefone(this.telefone);
                return entity;
        }
}
