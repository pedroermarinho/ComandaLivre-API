package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.record.CommandRecord;
import io.github.pedroermarinho.comandalivreapi.domain.record.ProductOfCommandRecord;
import io.github.pedroermarinho.comandalivreapi.domain.record.ProductRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.command.SearchCommand;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.product.SearchProduct;
import io.github.pedroermarinho.comandalivreapi.infra.forms.ProductOfCommandForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductOfCommandConvert implements Converter<ProductOfCommandForm, ProductOfCommandRecord> {

    private final SearchCommand searchCommand;
    private final SearchProduct searchProduct;

    public ProductOfCommandConvert(SearchCommand searchCommand, SearchProduct searchProduct) {
        this.searchCommand = searchCommand;
        this.searchProduct = searchProduct;
    }

    @Override
    public ProductOfCommandRecord convert(ProductOfCommandForm source) {
        final CommandRecord commandRecord = searchCommand.searchCommandById(source.commandId());
        final ProductRecord productRecord = searchProduct.searchProductById(source.productId());
        return new ProductOfCommandRecord(source.amount(), commandRecord, productRecord);
    }
}
