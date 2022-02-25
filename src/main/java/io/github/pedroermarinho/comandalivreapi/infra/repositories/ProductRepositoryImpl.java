package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductDTO;
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
    private final ProductConvert convert = new ProductConvert();

    public ProductRepositoryImpl(ProductDataSource productDataSource) {
        this.productDataSource = productDataSource;
    }

    @Override
    public List<ProductDTO> findAll() {
        return convert.formEntity(productDataSource.findAll());
    }

    @Override
    public ProductDTO findById(UUID id) {
        return convert.formEntity(productDataSource.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Produdo não encontrado! Id: " + id + ", Tipo: " + ProductDTO.class.getName())));
    }

    @Override
    public ProductDTO create(ProductDTO param) {
        return convert.formEntity(productDataSource.save(convert.formDTO(param)));
    }

    @Override
    public ProductDTO update(UUID id, ProductDTO param) {
        throw new NotImplementedException();
    }

    @Override
    public ProductDTO disable(UUID id) {
        final ProductDTO productDTO = findById(id);
        productDTO.setStatus(false);
        return convert.formEntity(productDataSource.save(convert.formDTO(productDTO)));
    }

    @Override
    public ProductDTO enable(UUID id) {
        final ProductDTO productDTO = findById(id);
        productDTO.setStatus(true);
        return convert.formEntity(productDataSource.save(convert.formDTO(productDTO)));
    }

}
