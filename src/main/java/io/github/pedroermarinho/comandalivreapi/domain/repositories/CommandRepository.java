package io.github.pedroermarinho.comandalivreapi.domain.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;

import java.util.UUID;

public interface CommandRepository extends GenericRepository<CommandDTO> {

    CommandDTO updatePaidOff(UUID id, boolean paidOff);
}
