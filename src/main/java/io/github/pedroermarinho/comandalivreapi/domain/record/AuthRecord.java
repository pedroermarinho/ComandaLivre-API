package io.github.pedroermarinho.comandalivreapi.domain.record;

import java.io.Serializable;

public record AuthRecord(
        String token,
        UserRecord user
) implements Serializable {

}
