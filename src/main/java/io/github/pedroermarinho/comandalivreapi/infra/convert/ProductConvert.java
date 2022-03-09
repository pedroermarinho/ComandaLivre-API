package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.OrganizationDTO;
import io.github.pedroermarinho.comandalivreapi.domain.dtos.ProductDTO;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.organization.SearchOrganization;
import io.github.pedroermarinho.comandalivreapi.infra.forms.ProductForm;
import org.springframework.core.convert.converter.Converter;

public class ProductConvert implements Converter< ProductForm,ProductDTO> {

    private final SearchOrganization searchOrganization;

    public ProductConvert(SearchOrganization searchOrganization) {
        this.searchOrganization = searchOrganization;
    }

    @Override
    public ProductDTO convert(ProductForm source) {
        final OrganizationDTO organizationDTO = searchOrganization.searchOrganizationById(source.getOrganizationId());
        return new ProductDTO(source.getName(),source.getDescription(),source.getPrice(),organizationDTO);
    }
}
