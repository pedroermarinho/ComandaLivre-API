package io.github.pedroermarinho.comandalivreapi.infra.forms;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Employeeform {

    private String registration;

    private String userId;
}
