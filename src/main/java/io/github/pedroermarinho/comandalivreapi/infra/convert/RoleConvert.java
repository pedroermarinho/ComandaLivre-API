package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.RoleDTO;
import io.github.pedroermarinho.comandalivreapi.infra.forms.RoleForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleConvert implements Converter<RoleForm, RoleDTO> {

    @Override
    public RoleDTO convert(RoleForm source) {
        return new RoleDTO(source.getName(), source.getDescription());
    }
}
