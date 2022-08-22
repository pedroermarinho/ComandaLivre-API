package io.github.pedroermarinho.comandalivreapi.presenter.controllers.product;

import io.github.pedroermarinho.comandalivreapi.domain.record.ProductRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.product.RegisterProduct;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.ProductPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.ProductConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.ProductForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(value = PathRest.API + PathRest.VERSION + ProductPathRest.PRODUCT_REGISTER)
@Tag(name = "Produto", description = "Operações do produto")
public class ProductRegisterController {

    private final RegisterProduct registerProduct;
    private final ProductConvert productConvert;

    public ProductRegisterController(RegisterProduct registerProduct, ProductConvert productConvert) {
        this.registerProduct = registerProduct;
        this.productConvert = productConvert;
    }

    @Operation(summary = "Cadastrar novo produto")
    @PostMapping
    public ResponseEntity<ProductRecord> registerProduct(ProductForm productForm) {
        final ProductRecord product = registerProduct.execute(productConvert.convert(productForm));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.id())
                .toUri();

        return ResponseEntity.created(uri).body(product);
    }

}
