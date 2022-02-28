package io.github.pedroermarinho.comandalivreapi.presenter.controllers.product;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.product.SearchProduct;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.ProductPathRest;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + ProductPathRest.PRODUCT_SEARCH)
public class SearchProductController {

    private final SearchProduct searchProduct;

    public SearchProductController(SearchProduct searchProduct) {
        this.searchProduct = searchProduct;
    }

    @Operation(summary = "Lista de produtos")
    @GetMapping
    public ResponseEntity<List<ProductDTO>> searchProductAll() {
        final List<ProductDTO> products = searchProduct.searchProductAll();
        return ResponseEntity.ok().body(products);
    }

    @Operation(summary = "Buscar produto por id")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> searchProductById(@PathVariable UUID id) {
        final ProductDTO product = searchProduct.searchProductById(id);
        return ResponseEntity.ok().body(product);
    }

}
