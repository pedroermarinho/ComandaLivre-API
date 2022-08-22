package io.github.pedroermarinho.comandalivreapi.domain.usecases.command;

import io.github.pedroermarinho.comandalivreapi.domain.record.ProductOfCommandRecord;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.ProductOfCommandRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddProductToCommand {
    private final ProductOfCommandRepository productofcommandRepository;

    public AddProductToCommand(ProductOfCommandRepository productofcommandRepository) {
        this.productofcommandRepository = productofcommandRepository;
    }

    @Transactional
    public ProductOfCommandRecord execute(ProductOfCommandRecord payload) {

        UtilValidation.objectNotNullValidationThrow(payload);
        UtilValidation.idNotNullValidationThrow(payload.product().id());

        return productofcommandRepository.create(payload).fold(
                throwable -> {
                    throw throwable;
                },
                result -> result);
    }
}
