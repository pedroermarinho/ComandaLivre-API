package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductDTO;
import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductOfCommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.command.SearchCommand;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.product.SearchProduct;
import io.github.pedroermarinho.comandalivreapi.infra.forms.ProductOfCommandForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductOfCommandConvert implements Converter<ProductOfCommandForm, ProductOfCommandDTO> {

    private final SearchCommand searchCommand;
    private final SearchProduct searchProduct;

    public ProductOfCommandConvert(SearchCommand searchCommand, SearchProduct searchProduct) {
        this.searchCommand = searchCommand;
        this.searchProduct = searchProduct;
    }

    @Override
    public ProductOfCommandDTO convert(ProductOfCommandForm source) {
        final CommandDTO commandDTO = searchCommand.searchCommandById(source.getCommandId());
        final ProductDTO productDTO = searchProduct.searchProductById(source.getProductId());
        return new ProductOfCommandDTO(source.getAmount(), commandDTO, productDTO);
    }
}
