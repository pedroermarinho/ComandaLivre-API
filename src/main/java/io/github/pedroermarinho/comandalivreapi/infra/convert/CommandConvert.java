package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.record.CommandRecord;
import io.github.pedroermarinho.comandalivreapi.infra.forms.CommandForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommandConvert implements Converter<CommandForm, CommandRecord> {

    @Override
    public CommandRecord convert(CommandForm source) {
        return new CommandRecord(source.paidOff(), source.identification());
    }
}
