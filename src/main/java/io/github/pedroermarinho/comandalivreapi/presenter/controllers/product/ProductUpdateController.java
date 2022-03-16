package io.github.pedroermarinho.comandalivreapi.presenter.controllers.product;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.product.UpdateProduct;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.ProductPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.convert.ProductConvert;
import io.github.pedroermarinho.comandalivreapi.infra.forms.ProductForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + ProductPathRest.PRODUCT_UPDATE)
@Tag(name = "Produto", description = "Operações do produto")
public class ProductUpdateController {

    private final UpdateProduct updateProduct;
    private final ProductConvert productConvert;

    public ProductUpdateController(UpdateProduct updateProduct, ProductConvert productConvert) {
        this.updateProduct = updateProduct;
        this.productConvert = productConvert;
    }

    @Operation(summary = "Atualizar produto")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable UUID id, @Valid @RequestBody ProductForm productForm) {
        final ProductDTO product = updateProduct.execute(id, productConvert.convert(productForm));
        return ResponseEntity.ok().body(product);
    }
}
