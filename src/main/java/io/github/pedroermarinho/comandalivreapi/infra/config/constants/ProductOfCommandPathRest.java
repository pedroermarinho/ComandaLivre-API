package io.github.pedroermarinho.comandalivreapi.infra.config.constants;

public class ProductOfCommandPathRest {

    private ProductOfCommandPathRest() {
        throw new IllegalStateException("Utility class");
    }

    public static final String PRODUCTOFCOMMAND = "/productofcommand";
    public static final String PRODUCTOFCOMMAND_REGISTER = PRODUCTOFCOMMAND;
    public static final String PRODUCTOFCOMMAND_SEARCH = PRODUCTOFCOMMAND;
    public static final String PRODUCTOFCOMMAND_UPDATE = PRODUCTOFCOMMAND;
    public static final String PRODUCTOFCOMMAND_STATUS = PRODUCTOFCOMMAND + "/disable";
}
