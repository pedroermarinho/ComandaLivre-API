package io.github.pedroermarinho.comandalivreapi.infra.datasources;

import io.github.pedroermarinho.comandalivreapi.domain.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductDataSource extends JpaRepository<ProductEntity, UUID> {

}
