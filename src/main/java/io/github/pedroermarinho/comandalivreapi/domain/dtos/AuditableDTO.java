package io.github.pedroermarinho.comandalivreapi.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public abstract class AuditableDTO {

    protected UUID id;

    protected UUID createdById;

    protected UUID modifiedById;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected Date creationDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected Date lastModifiedDate;

    protected Boolean status = true;
}
