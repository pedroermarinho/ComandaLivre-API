package io.github.pedroermarinho.comandalivreapi.domain.validation;

public interface Validation<T> {
    boolean validation(T value);
    void validationThrow(T value);
}
