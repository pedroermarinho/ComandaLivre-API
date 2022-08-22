package io.github.pedroermarinho.comandalivreapi.infra.convert;

import io.github.pedroermarinho.comandalivreapi.domain.record.EmployeeRecord;
import io.github.pedroermarinho.comandalivreapi.domain.record.UserRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.user.SearchUser;
import io.github.pedroermarinho.comandalivreapi.infra.forms.EmployeeForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConvert implements Converter<EmployeeForm, EmployeeRecord> {

    private final SearchUser searchUser;

    public EmployeeConvert(SearchUser searchUser) {
        this.searchUser = searchUser;
    }

    @Override
    public EmployeeRecord convert(EmployeeForm source) {
        final UserRecord userRecord = searchUser.searchUserById(source.userId());
        return new EmployeeRecord(source.registration(), userRecord);
    }
}
