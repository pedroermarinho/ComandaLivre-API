package io.github.pedroermarinho.comandalivreapi.infra.seed;

import io.github.pedroermarinho.comandalivreapi.infra.seed.data.*;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;
import java.util.List;

public class DatabaseSeeder implements CommandLineRunner {

    private final List<DataSeed> dataSeeds;

    public DatabaseSeeder() {
        this.dataSeeds = Arrays.asList(
                new AddressData(),
                new CommandData(),
                new EmployeeAtOrganizationData(),
                new EmployeeData(),
                new OrganizationData(),
                new ProductData(),
                new ProductData(),
                new ProductOfCommandData(),
                new RoleData(),
                new UserData()
        );
    }

    @Override
    public void run(String... args) throws Exception {
        dataSeeds.forEach(DataSeed::create);
    }
}
