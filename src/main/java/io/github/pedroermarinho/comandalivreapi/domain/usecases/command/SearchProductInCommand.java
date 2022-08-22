package io.github.pedroermarinho.comandalivreapi.domain.usecases.command;

import io.github.pedroermarinho.comandalivreapi.domain.record.ProductOfCommandRecord;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.ProductOfCommandRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SearchProductInCommand {

    private final ProductOfCommandRepository productOfCommandRepository;

    public SearchProductInCommand(ProductOfCommandRepository productofcommandRepository) {
        this.productOfCommandRepository = productofcommandRepository;
    }

    public ProductOfCommandRecord searchProductInCommandById(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);

        final var productOfCommand = productOfCommandRepository.findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                result -> result);
        UtilValidation.statusEnableValidationThrow(productOfCommand.status());

        return productOfCommand;
    }

    public List<ProductOfCommandRecord> searchProductInCommandAll() {
        return productOfCommandRepository.findAll().stream().filter(ProductOfCommandRecord::status).toList();
    }

}
