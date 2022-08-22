package io.github.pedroermarinho.comandalivreapi.domain.usecases.command;

import io.github.pedroermarinho.comandalivreapi.domain.record.ProductOfCommandRecord;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.ProductOfCommandRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class EditProductToCommand {

    private final ProductOfCommandRepository productofcommandRepository;

    public EditProductToCommand(ProductOfCommandRepository productofcommandRepository) {
        this.productofcommandRepository = productofcommandRepository;
    }

    @Transactional
    public ProductOfCommandRecord execute(UUID id, ProductOfCommandRecord payload) {

        UtilValidation.idNotNullValidationThrow(id);
        UtilValidation.objectNotNullValidationThrow(payload);

        return productofcommandRepository.update(id, payload).fold(
                throwable -> {
                    throw throwable;
                },
                result -> result);
    }
}
