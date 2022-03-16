package io.github.pedroermarinho.comandalivreapi.presenter.controllers.product;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.product.StatusProduct;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.ProductPathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + ProductPathRest.PRODUCT_STATUS)
@Tag(name = "Produto", description = "Operações do produto")
public class ProductStatusController {

    private final StatusProduct statusProduct;

    public ProductStatusController(StatusProduct statusProduct) {
        this.statusProduct = statusProduct;
    }

    @Operation(summary = "Desabilitar produto")
    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> disableProduct(@PathVariable UUID id) {
        final ProductDTO product = statusProduct.disableProduct(id);
        return ResponseEntity.ok().body(product);
    }
}
