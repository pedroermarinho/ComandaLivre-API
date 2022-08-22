package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.record.AddressRecord;
import io.github.pedroermarinho.comandalivreapi.domain.record.OrganizationRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.address.SearchAddress;
import io.github.pedroermarinho.comandalivreapi.infra.forms.OrganizationForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrganizationConvert implements Converter<OrganizationForm, OrganizationRecord> {

    private final SearchAddress searchAddress;

    public OrganizationConvert(SearchAddress searchAddress) {
        this.searchAddress = searchAddress;
    }

    @Override
    public OrganizationRecord convert(OrganizationForm source) {
        final AddressRecord addressRecord = searchAddress.searchAddressById(source.addressId());
        return new OrganizationRecord(source.name(), source.phone(), addressRecord);
    }
}
