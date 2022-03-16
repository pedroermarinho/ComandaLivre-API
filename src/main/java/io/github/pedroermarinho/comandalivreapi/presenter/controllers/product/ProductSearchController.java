package io.github.pedroermarinho.comandalivreapi.presenter.controllers.product;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.product.SearchProduct;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.ProductPathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + ProductPathRest.PRODUCT_SEARCH)
@Tag(name = "Produto", description = "Operações do produto")
public class ProductSearchController {

    private final SearchProduct searchProduct;

    public ProductSearchController(SearchProduct searchProduct) {
        this.searchProduct = searchProduct;
    }

    @Operation(summary = "Lista de produtos")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)))
            )
    })
    @GetMapping
    public ResponseEntity<List<ProductDTO>> searchProductAll() {
        final List<ProductDTO> products = searchProduct.searchProductAll();
        return ResponseEntity.ok().body(products);
    }

    @Operation(summary = "Buscar produto por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(schema = @Schema(implementation = ProductDTO.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> searchProductById(@PathVariable UUID id) {
        final ProductDTO product = searchProduct.searchProductById(id);
        return ResponseEntity.ok().body(product);
    }

}
