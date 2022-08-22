package io.github.pedroermarinho.comandalivreapi.infra.seed.data;

import io.github.pedroermarinho.comandalivreapi.domain.usecases.organization.RegisterOrganization;
import org.springframework.stereotype.Component;

@Component
public class OrganizationData implements DataSeed {

    private final RegisterOrganization registerOrganization;

    public OrganizationData(RegisterOrganization registerOrganization) {
        this.registerOrganization = registerOrganization;
    }

    @Override
    public void create() {

    }
}
