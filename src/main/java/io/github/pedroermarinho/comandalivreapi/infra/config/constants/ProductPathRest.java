package io.github.pedroermarinho.comandalivreapi.infra.config.constants;

public class ProductPathRest {

    private ProductPathRest() {
        throw new IllegalStateException("Utility class");
    }

    public static final String PRODUCT = "/product";
    public static final String PRODUCT_REGISTER = PRODUCT;
    public static final String PRODUCT_SEARCH = PRODUCT;
    public static final String PRODUCT_UPDATE = PRODUCT;
    public static final String PRODUCT_STATUS = PRODUCT + "/disable";
}
