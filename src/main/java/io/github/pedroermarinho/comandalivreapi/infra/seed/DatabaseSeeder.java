package io.github.pedroermarinho.comandalivreapi.infra.seed;

import io.github.pedroermarinho.comandalivreapi.infra.seed.data.DataSeed;
import io.github.pedroermarinho.comandalivreapi.infra.seed.data.UserData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final List<DataSeed> dataSeeds;

    public DatabaseSeeder(
            UserData userData
    ) {
        this.dataSeeds = List.of(
                userData
        );
    }

    @Override
    public void run(String... args) {
        dataSeeds.forEach(DataSeed::create);
    }
}
