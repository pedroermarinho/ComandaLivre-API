package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.record.UserRecord;
import io.github.pedroermarinho.comandalivreapi.infra.forms.UserForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConvert implements Converter<UserForm, UserRecord> {

    @Override
    public UserRecord convert(UserForm source) {
        return new UserRecord(
                source.name(),
                source.email(),
                source.username(),
                source.password(),
                source.phone()
        );
    }
}
