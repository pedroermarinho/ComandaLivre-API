package io.github.pedroermarinho.comandalivreapi.presenter.controllers.product;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.product.RegisterProduct;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.ProductPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.ProductConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.ProductForm;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + ProductPathRest.PRODUCT_REGISTER)
public class RegisterProductController {

    private final RegisterProduct registerProduct;
    private final ProductConvert convert = new ProductConvert();

    public RegisterProductController(RegisterProduct registerProduct) {
        this.registerProduct = registerProduct;
    }

    @Operation(summary = "Cadastrar novo produto")
    @PostMapping
    public ResponseEntity<ProductDTO> registerProduct(ProductForm productForm) {
        final ProductDTO product = registerProduct.execute(convert.fromForm(productForm));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(uri).body(product);
    }

}
