package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.AddressDTO;
import io.github.pedroermarinho.comandalivreapi.domain.dtos.OrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.address.SearchAddress;
import io.github.pedroermarinho.comandalivreapi.infra.forms.OrganizationForm;
import org.springframework.core.convert.converter.Converter;

public class OrganizationConvert implements Converter< OrganizationForm,OrganizationDTO> {

    private final SearchAddress searchAddress;

    public OrganizationConvert(SearchAddress searchAddress) {
        this.searchAddress = searchAddress;
    }

    @Override
    public OrganizationDTO convert(OrganizationForm source) {
        final AddressDTO addressDTO = searchAddress.searchAddressById(source.getAddressId());
        return new OrganizationDTO(source.getName(),source.getTelefone(),addressDTO);
    }
}
