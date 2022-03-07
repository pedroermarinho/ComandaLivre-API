package io.github.pedroermarinho.comandalivreapi.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

public record AuditableDTO(

         UUID id,

         UUID createdById,

         UUID modifiedById,

        @JsonFormat(pattern = "dd/MM/yyyy")
         Date creationDate,

        @JsonFormat(pattern = "dd/MM/yyyy")
         Date lastModifiedDate,

         Boolean status
) {

}
