package io.github.pedroermarinho.comandalivreapi.domain.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.record.CommandRecord;
import io.vavr.control.Either;

import java.util.UUID;

public interface CommandRepository extends GenericRepository<CommandRecord> {

    Either<RuntimeException, CommandRecord> updatePaidOff(UUID id, boolean paidOff);
}
