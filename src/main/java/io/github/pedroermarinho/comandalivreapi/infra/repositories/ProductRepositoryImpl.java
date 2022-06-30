package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.ProductEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.ProductRepository;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.ProductDataSource;
import io.vavr.control.Either;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductDataSource productDataSource;

    public ProductRepositoryImpl(ProductDataSource productDataSource) {
        this.productDataSource = productDataSource;
    }

    @Override
    public List<ProductDTO> findAll() {
        return productDataSource.findAll().stream().map(ProductDTO::new).toList();
    }

    @Override
    public Either<RuntimeException, ProductDTO> findById(UUID id) {
        return productDataSource.findById(id).<Either<RuntimeException, ProductDTO>>map(entity -> Either.right(new ProductDTO(entity)))
                .orElseGet(() -> Either.left(new ObjectNotFoundException(
                        "Produdo não encontrado! Id: " + id + ", Tipo: " + ProductDTO.class.getName())));
    }

    @Override
    public Either<RuntimeException, ProductDTO> create(ProductDTO param) {
        return Either.right(new ProductDTO(productDataSource.save(param.toEntity())));
    }

    @Override
    public Either<RuntimeException, ProductDTO> update(UUID id, ProductDTO param) {
        return Either.left(new NotImplementedException());
    }

    @Override
    public Either<RuntimeException, ProductDTO> disable(UUID id) {
        final ProductEntity productEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                ProductDTO::toEntity);
        productEntity.setStatus(false);
        return Either.right(new ProductDTO(productDataSource.save(productEntity)));
    }

    @Override
    public Either<RuntimeException, ProductDTO> enable(UUID id) {
        final ProductEntity productEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                ProductDTO::toEntity);
        productEntity.setStatus(true);
        return Either.right(new ProductDTO(productDataSource.save(productEntity)));
    }

    @Override
    public Either<RuntimeException, Long> count() {
        return Either.right(productDataSource.count());
    }

}
