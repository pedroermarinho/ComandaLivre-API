package io.github.pedroermarinho.comandalivreapi.infra.repositories;

import io.github.pedroermarinho.comandalivreapi.domain.entities.CommandEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.ObjectNotFoundException;
import io.github.pedroermarinho.comandalivreapi.domain.record.CommandRecord;
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
    public List<CommandRecord> findAll() {
        return commandDataSource.findAll().stream().map(CommandRecord::new).toList();
    }

    @Override
    public Either<RuntimeException, CommandRecord> findById(UUID id) {
        return commandDataSource.findById(id).<Either<RuntimeException, CommandRecord>>map(entity -> Either.right(new CommandRecord(entity)))
                .orElseGet(() -> Either.left(new ObjectNotFoundException(
                        "Comanda não encontrado! Id: " + id + ", Tipo: " + CommandRecord.class.getName())));
    }

    @Override
    public Either<RuntimeException, CommandRecord> create(CommandRecord param) {
        return Either.right(new CommandRecord(commandDataSource.save(param.toEntity())));
    }

    @Override
    public Either<RuntimeException, CommandRecord> update(UUID id, CommandRecord param) {
        final CommandEntity commandEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                CommandRecord::toEntity);
        commandEntity.setIdentification(param.identification());
        return Either.right(new CommandRecord(commandDataSource.save(commandEntity)));
    }

    @Override
    public Either<RuntimeException, CommandRecord> disable(UUID id) {
        final CommandEntity commandEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                CommandRecord::toEntity);
        commandEntity.setStatus(false);
        return Either.right(new CommandRecord(commandDataSource.save(commandEntity)));
    }

    @Override
    public Either<RuntimeException, CommandRecord> enable(UUID id) {
        final CommandEntity commandEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                CommandRecord::toEntity);
        commandEntity.setStatus(true);
        return Either.right(new CommandRecord(commandDataSource.save(commandEntity)));
    }

    @Override
    public Either<RuntimeException, Long> count() {
        return Either.right(commandDataSource.count());
    }

    @Override
    public Either<RuntimeException, CommandRecord> updatePaidOff(UUID id, boolean paidOff) {
        final CommandEntity commandEntity = findById(id).fold(
                throwable -> {
                    throw throwable;
                },
                CommandRecord::toEntity);
        commandEntity.setPaidOff(paidOff);
        return Either.right(new CommandRecord(commandDataSource.save(commandEntity)));
    }
}
