package io.github.pedroermarinho.comandalivreapi.domain.usecases.address;

import io.github.pedroermarinho.comandalivreapi.domain.record.AddressRecord;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.AddressRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class StatusAddress {

    private final AddressRepository addressRepository;

    public StatusAddress(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public AddressRecord disableAddress(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return addressRepository.disable(id).fold(
                throwable -> {
                    throw throwable;
                },
                value -> value);
    }

    @Transactional
    public AddressRecord enableAddress(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return addressRepository.enable(id).fold(
                throwable -> {
                    throw throwable;
                },
                value -> value);
    }
}
