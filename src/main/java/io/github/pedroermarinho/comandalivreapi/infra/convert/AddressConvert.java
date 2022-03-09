package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.AddressDTO;
import io.github.pedroermarinho.comandalivreapi.infra.forms.AddressForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;



@Component
public class AddressConvert implements Converter<AddressForm,AddressDTO> {


    @Override
    public AddressDTO convert(AddressForm source) {
        return new AddressDTO(
                source.getCep(),
                source.getLogradouro(),
                source.getBairro(),
                source.getLocalidade(),
                source.getUf(),
                source.getNumber()
        );
    }
}
