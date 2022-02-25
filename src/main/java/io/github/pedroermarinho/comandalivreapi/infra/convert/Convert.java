package io.github.pedroermarinho.comandalivreapi.infra.convert;

import java.util.List;

public interface Convert<E, D, F> {
    D formEntity(E entity);

    List<D> formEntity(List<E> entityList);

    E formDTO(D dto);

    D fromForm(F form);
}
