package io.github.pedroermarinho.comandalivreapi.infra.config.constants;

public class RolePathRest {

    private RolePathRest() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ROLE = "/role";
    public static final String ROLE_REGISTER = ROLE;
    public static final String ROLE_SEARCH = ROLE;
    public static final String ROLE_UPDATE = ROLE;
    public static final String ROLE_STATUS = ROLE + "/disable";
}
