package io.github.pedroermarinho.comandalivreapi.presenter.controllers.product_of_command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductOfCommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.product_of_command.SearchProductOfCommand;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.ProductOfCommandPathRest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + ProductOfCommandPathRest.PRODUCTOFCOMMAND_SEARCH)
public class SearchProductOfCommandController {

    private final SearchProductOfCommand searchProductOfCommand;

    public SearchProductOfCommandController(SearchProductOfCommand searchProductOfCommand) {
        this.searchProductOfCommand = searchProductOfCommand;
    }

    @GetMapping
    public ResponseEntity<List<ProductOfCommandDTO>> searchProductOfCommandAll() {
        final List<ProductOfCommandDTO> productofcommands = searchProductOfCommand.searchProductOfCommandAll();
        return ResponseEntity.ok().body(productofcommands);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOfCommandDTO> searchProductOfCommandById(@PathVariable UUID id) {
        final ProductOfCommandDTO productofcommand = searchProductOfCommand.searchProductOfCommandById(id);
        return ResponseEntity.ok().body(productofcommand);
    }

}
