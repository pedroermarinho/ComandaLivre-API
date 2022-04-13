package io.github.pedroermarinho.comandalivreapi.infra.seed;

import io.github.pedroermarinho.comandalivreapi.domain.usecases.organization.RegisterOrganization;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.user.RegisterUser;
import io.github.pedroermarinho.comandalivreapi.infra.seed.data.*;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;
import java.util.List;

public class DatabaseSeeder implements CommandLineRunner {

    private final List<DataSeed> dataSeeds;

    public DatabaseSeeder(
            RegisterUser registerUser,
            RegisterOrganization registerOrganization
    ) {
        this.dataSeeds = Arrays.asList(
                new AddressData(),
                new CommandData(),
                new EmployeeAtOrganizationData(),
                new EmployeeData(),
                new OrganizationData(registerOrganization),
                new ProductData(),
                new ProductData(),
                new ProductOfCommandData(),
                new RoleData(),
                new UserData(registerUser)
        );
    }

    @Override
    public void run(String... args) throws Exception {
        dataSeeds.forEach(DataSeed::create);
    }
}
