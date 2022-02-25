package io.github.pedroermarinho.comandalivreapi.domain.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommandDTO extends AuditableDTO {

    private Boolean paidOff;

    private String identification;

}
