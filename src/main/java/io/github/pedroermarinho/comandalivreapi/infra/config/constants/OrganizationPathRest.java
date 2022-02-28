package io.github.pedroermarinho.comandalivreapi.infra.config.constants;

public class OrganizationPathRest {

    private OrganizationPathRest() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ORGANIZATION = "/organization";
    public static final String ORGANIZATION_REGISTER = ORGANIZATION;
    public static final String ORGANIZATION_SEARCH = ORGANIZATION;
    public static final String ORGANIZATION_UPDATE = ORGANIZATION;
    public static final String ORGANIZATION_STATUS = ORGANIZATION + "/disable";
}
