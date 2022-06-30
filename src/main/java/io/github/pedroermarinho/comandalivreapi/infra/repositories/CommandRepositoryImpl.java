package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.dtos.CommandDTO;
import io.github.pedroermarinho.comandalivreapi.domain.entities.CommandEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.repositories.CommandRepository;
import io.github.pedroermarinho.comandalivreapi.infra.datasources.CommandDataSource;
import io.vavr.control.Either;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CommandRepositoryImpl implements CommandRepository {

    private final CommandDataSource commandDataSource;

    public CommandRepositoryImpl(CommandDataSource commandDataSource) {
        this.commandDataSource = commandDataSource;
    }

    @Override
    public List<CommandDTO> findAll() {
        return commandDataSource.findAll().stream().map(CommandDTO::new).toList();
    }

    @Override
    public Either<RuntimeException, CommandDTO> findById(UUID id) {
        return commandDataSource.findById(id).<Either<RuntimeException, CommandDTO>>map(entity -> Either.right(new CommandDTO(entity)))
                .orElseGet(() -> Either.left(new ObjectNotFoundException(
                        "Comanda não encontrado! Id: " + id + ", Tipo: " + CommandDTO.class.getName())));
    }

    @Override
    public Either<RuntimeException, CommandDTO> create(CommandDTO param) {
        return Either.right(new CommandDTO(commandDataSource.save(param.toEntity())));
    }

    @Override
    public Either<RuntimeException, CommandDTO> update(UUID id, CommandDTO param) {
        final CommandEntity commandEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                CommandDTO::toEntity);
        commandEntity.setIdentification(param.identification());
        return Either.right(new CommandDTO(commandDataSource.save(commandEntity)));
    }

    @Override
    public Either<RuntimeException, CommandDTO> disable(UUID id) {
        final CommandEntity commandEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                CommandDTO::toEntity);
        commandEntity.setStatus(false);
        return Either.right(new CommandDTO(commandDataSource.save(commandEntity)));
    }

    @Override
    public Either<RuntimeException, CommandDTO> enable(UUID id) {
        final CommandEntity commandEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                CommandDTO::toEntity);
        commandEntity.setStatus(true);
        return Either.right(new CommandDTO(commandDataSource.save(commandEntity)));
    }

    @Override
    public Either<RuntimeException, Long> count() {
        return Either.right(commandDataSource.count());
    }

    @Override
    public Either<RuntimeException, CommandDTO> updatePaidOff(UUID id, boolean paidOff) {
        final CommandEntity commandEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                CommandDTO::toEntity);
        commandEntity.setPaidOff(paidOff);
        return Either.right(new CommandDTO(commandDataSource.save(commandEntity)));
    }
}
