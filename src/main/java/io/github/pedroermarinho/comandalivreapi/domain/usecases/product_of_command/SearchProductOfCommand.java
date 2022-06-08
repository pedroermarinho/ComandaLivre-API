package io.github.pedroermarinho.comandalivreapi.domain.usecases.product_of_command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductOfCommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.ProductOfCommandRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SearchProductOfCommand {

    private final ProductOfCommandRepository productofcommandRepository;

    public SearchProductOfCommand(ProductOfCommandRepository productofcommandRepository) {
        this.productofcommandRepository = productofcommandRepository;
    }

    public ProductOfCommandDTO searchProductOfCommandById(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return productofcommandRepository.findById(id);
    }

    public List<ProductOfCommandDTO> searchProductOfCommandAll() {
        return productofcommandRepository.findAll();
    }

}
