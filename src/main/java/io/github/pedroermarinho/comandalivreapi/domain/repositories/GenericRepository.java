package io.github.pedroermarinho.comandalivreapi.domain.repositories;

import java.util.List;
import java.util.UUID;

public interface GenericRepository<D> {
    List<D> findAll();

    D findById(UUID id);

    D create(D param);

    D update(UUID id, D param);

    D disable(UUID id);

    D enable(UUID id);
}
