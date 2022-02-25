package io.github.pedroermarinho.comandalivreapi.domain.usecases.product;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.ProductRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UpdateAddress {

    private final ProductRepository productRepository;

    public UpdateAddress(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductDTO execute(UUID id, ProductDTO productParam) {

        final List<Validation<UUID>> idValidations = Arrays.asList(new NotNullValidation<>());

        idValidations.forEach(validation -> validation.validationThrow(id));

        return productRepository.update(id, productParam);
    }
}
