package io.github.pedroermarinho.comandalivreapi.domain.usecases.product;

import io.github.pedroermarinho.comandalivreapi.domain.record.ProductRecord;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.ProductRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SearchProduct {

    private final ProductRepository productRepository;

    public SearchProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductRecord searchProductById(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return productRepository.findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                result -> result);
    }

    public List<ProductRecord> searchProductAll() {
        return productRepository.findAll();
    }

}
