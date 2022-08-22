package io.github.pedroermarinho.comandalivreapi.domain.usecases.address;

import io.github.pedroermarinho.comandalivreapi.domain.record.AddressRecord;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.AddressRepository;
import io.github.pedroermarinho.comandalivreapi.domain.validation.UtilValidation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SearchAddress {

    private final AddressRepository addressRepository;

    public SearchAddress(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressRecord searchAddressById(UUID id) {
        UtilValidation.idNotNullValidationThrow(id);
        return addressRepository.findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                value -> value);
    }

    public List<AddressRecord> searchAddressAll() {
        return addressRepository.findAll();
    }

}
