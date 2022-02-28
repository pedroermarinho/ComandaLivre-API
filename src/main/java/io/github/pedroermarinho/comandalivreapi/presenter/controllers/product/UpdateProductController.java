package io.github.pedroermarinho.comandalivreapi.presenter.controllers.product;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.product.UpdateProduct;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.ProductPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.ProductConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.ProductForm;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + ProductPathRest.PRODUCT_UPDATE)
public class UpdateProductController {

    private final UpdateProduct updateProduct;
    private final ProductConvert convert = new ProductConvert();

    public UpdateProductController(UpdateProduct updateProduct) {
        this.updateProduct = updateProduct;
    }

    @Operation(summary = "Atualizar produto")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable UUID id, @Valid @RequestBody ProductForm productForm) {
        final ProductDTO product = updateProduct.execute(id, convert.fromForm(productForm));
        return ResponseEntity.ok().body(product);
    }
}
