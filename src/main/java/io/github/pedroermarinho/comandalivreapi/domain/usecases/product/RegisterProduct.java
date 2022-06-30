package io.github.pedroermarinho.comandalivreapi.domain.usecases.product;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductDTO;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.ProductRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.NotNullValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import io.github.pedroermarinho.comandalivreapi.domain.validation.Validation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegisterProduct {

    private final ProductRepository productRepository;

    public RegisterProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductDTO execute(ProductDTO productRegister) {
        UtilValidation.objectNotNullValidationThrow(productRegister);

        final List<Validation<String>> validations = List.of(new NotNullValidation<>());
        validations.forEach(validation -> validation.validationThrow(productRegister.name()));

        return productRepository.create(productRegister).fold(
                throwable -> {
                    throw throwable;
                },
                result -> result);
    }

}
