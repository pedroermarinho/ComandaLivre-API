package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.entities.ProductEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.record.ProductRecord;
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
    public List<ProductRecord> findAll() {
        return productDataSource.findAll().stream().map(ProductRecord::new).toList();
    }

    @Override
    public Either<RuntimeException, ProductRecord> findById(UUID id) {
        return productDataSource.findById(id).<Either<RuntimeException, ProductRecord>>map(entity -> Either.right(new ProductRecord(entity)))
                .orElseGet(() -> Either.left(new ObjectNotFoundException(
                        "Produdo não encontrado! Id: " + id + ", Tipo: " + ProductRecord.class.getName())));
    }

    @Override
    public Either<RuntimeException, ProductRecord> create(ProductRecord param) {
        return Either.right(new ProductRecord(productDataSource.save(param.toEntity())));
    }

    @Override
    public Either<RuntimeException, ProductRecord> update(UUID id, ProductRecord param) {
        return Either.left(new NotImplementedException());
    }

    @Override
    public Either<RuntimeException, ProductRecord> disable(UUID id) {
        final ProductEntity productEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                ProductRecord::toEntity);
        productEntity.setStatus(false);
        return Either.right(new ProductRecord(productDataSource.save(productEntity)));
    }

    @Override
    public Either<RuntimeException, ProductRecord> enable(UUID id) {
        final ProductEntity productEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                ProductRecord::toEntity);
        productEntity.setStatus(true);
        return Either.right(new ProductRecord(productDataSource.save(productEntity)));
    }

    @Override
    public Either<RuntimeException, Long> count() {
        return Either.right(productDataSource.count());
    }

}
