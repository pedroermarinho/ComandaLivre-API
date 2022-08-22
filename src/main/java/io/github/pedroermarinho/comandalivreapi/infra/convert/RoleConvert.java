package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.record.RoleRecord;
import io.github.pedroermarinho.comandalivreapi.infra.forms.RoleForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleConvert implements Converter<RoleForm, RoleRecord> {

    @Override
    public RoleRecord convert(RoleForm source) {
        return new RoleRecord(source.name(), source.description());
    }
}
