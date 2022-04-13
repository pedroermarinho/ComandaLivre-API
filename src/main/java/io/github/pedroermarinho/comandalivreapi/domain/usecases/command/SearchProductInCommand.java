package io.github.pedroermarinho.comandalivreapi.domain.usecases.command;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductOfCommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.ProductOfCommandRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.ObjectDisabledValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SearchProductInCommand {

    private final ProductOfCommandRepository productOfCommandRepository;

    public SearchProductInCommand(ProductOfCommandRepository productofcommandRepository) {
        this.productOfCommandRepository = productofcommandRepository;
    }

    public ProductOfCommandDTO searchProductInCommandById(UUID id) {
        final List<Validation<UUID>> validations = List.of(new NotNullValidation<>());

        validations.forEach(validation -> validation.validationThrow(id));

        final var result = productOfCommandRepository.findById(id);

        final List<Validation<Boolean>> statusValidations = List.of(
                new NotNullValidation<>(),
                new ObjectDisabledValidation()
        );

        statusValidations.forEach(validation -> validation.validationThrow(result.status()));

        return result;
    }

    public List<ProductOfCommandDTO> searchProductInCommandAll() {
        return productOfCommandRepository.findAll().stream().filter(ProductOfCommandDTO::status).toList();
    }
}
