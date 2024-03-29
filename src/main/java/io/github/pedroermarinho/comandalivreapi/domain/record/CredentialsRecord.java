package io.github.pedroermarinho.comandalivreapi.domain.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsRecord {
    String email;
    String password;
}
