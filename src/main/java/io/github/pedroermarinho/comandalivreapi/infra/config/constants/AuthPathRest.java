package io.github.pedroermarinho.comandalivreapi.infra.config.constants;

public class AuthPathRest {

    private AuthPathRest() {
        throw new IllegalStateException("Utility class");
    }

    public static final String auth = "/auth";
    public static final String login = "/login";
}
