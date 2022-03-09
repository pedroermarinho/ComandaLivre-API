package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.ProductEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.NotImplementedException;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.ProductRepository;
import io.github.pedroermarinho.comandalivreapi.infra.convert.ProductConvert;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.ProductDataSource;
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
    public ProductDTO findById(UUID id) {
        return new ProductDTO(productDataSource.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Produdo não encontrado! Id: " + id + ", Tipo: " + ProductDTO.class.getName())));
    }

    @Override
    public ProductDTO create(ProductDTO param) {
        return new ProductDTO(productDataSource.save(param.toEntity()));
    }

    @Override
    public ProductDTO update(UUID id, ProductDTO param) {
        throw new NotImplementedException();
    }

    @Override
    public ProductDTO disable(UUID id) {
        final ProductEntity productEntity = findById(id).toEntity();
        productEntity.setStatus(false);
        return new ProductDTO(productDataSource.save(productEntity));
    }

    @Override
    public ProductDTO enable(UUID id) {
        final ProductEntity productEntity = findById(id).toEntity();
        productEntity.setStatus(true);
        return new ProductDTO(productDataSource.save(productEntity));
    }

}
