package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;
import io.github.pedroermarinho.comandalivreapi.infra.forms.CommandForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommandConvert implements Converter<CommandForm, CommandDTO> {

    @Override
    public CommandDTO convert(CommandForm source) {
        return new CommandDTO(source.paidOff(), source.identification());
    }
}
