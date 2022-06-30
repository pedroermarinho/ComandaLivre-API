package io.github.pedroermarinho.comandalivreapi.domain.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;
import io.vavr.control.Either;

import java.util.UUID;

public interface CommandRepository extends GenericRepository<CommandDTO> {

    Either<RuntimeException, CommandDTO> updatePaidOff(UUID id, boolean paidOff);
}
