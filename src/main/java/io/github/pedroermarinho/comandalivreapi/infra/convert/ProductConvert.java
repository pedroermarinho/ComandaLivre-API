package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.record.OrganizationRecord;
import io.github.pedroermarinho.comandalivreapi.domain.record.ProductRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.organization.SearchOrganization;
import io.github.pedroermarinho.comandalivreapi.infra.forms.ProductForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductConvert implements Converter<ProductForm, ProductRecord> {

    private final SearchOrganization searchOrganization;

    public ProductConvert(SearchOrganization searchOrganization) {
        this.searchOrganization = searchOrganization;
    }

    @Override
    public ProductRecord convert(ProductForm source) {
        final OrganizationRecord organizationRecord = searchOrganization.searchOrganizationById(source.organizationId());
        return new ProductRecord(source.name(), source.description(), source.price(), organizationRecord);
    }
}
