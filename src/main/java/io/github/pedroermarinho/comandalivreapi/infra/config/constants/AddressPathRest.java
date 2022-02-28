package io.github.pedroermarinho.comandalivreapi.infra.config.constants;

public class AddressPathRest {

    private AddressPathRest() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ADDRESS = "/address";
    public static final String ADDRESS_REGISTER = ADDRESS;
    public static final String ADDRESS_SEARCH = ADDRESS;
    public static final String ADDRESS_UPDATE = ADDRESS;
    public static final String ADDRESS_STATUS = ADDRESS + "/disable";
}
