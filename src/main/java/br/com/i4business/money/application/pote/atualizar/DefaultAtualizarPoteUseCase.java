package br.com.i4business.money.application.pote.atualizar;

import br.com.i4business.money.domain.data.Identifier;
import br.com.i4business.money.domain.data.categoria.CategoriaGateway;
import br.com.i4business.money.domain.data.categoria.CategoriaID;
import br.com.i4business.money.domain.data.pote.Pote;
import br.com.i4business.money.domain.data.pote.PoteGateway;
import br.com.i4business.money.domain.data.pote.PoteID;
import br.com.i4business.money.domain.exception.DomainException;
import br.com.i4business.money.domain.exception.NotFoundException;
import br.com.i4business.money.domain.exception.NotificationException;
import br.com.i4business.money.domain.validation.Error;
import br.com.i4business.money.domain.validation.ValidationHandler;
import br.com.i4business.money.domain.validation.handler.Notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class DefaultAtualizarPoteUseCase extends AtualizarPoteUseCase {

    private final PoteGateway poteGateway;
    private final CategoriaGateway categoriaGateway;

    public DefaultAtualizarPoteUseCase(final PoteGateway poteGateway, final CategoriaGateway categoriaGateway) {
        this.poteGateway = Objects.requireNonNull(poteGateway);
        this.categoriaGateway = Objects.requireNonNull(categoriaGateway);
    }

    @Override
    public AtualizarPoteResponse execute(AtualizarPoteCommand command) {
        final var id = PoteID.de(command.id());
        final var nome = command.nome();
        final var descricao = command.descricao();
        final var isAtivo = command.isAtivo();
        final var categorias = toCategoriaID(command.categorias());

        final var pote = this.poteGateway.findById(id)
                .orElseThrow(notFound(id));

        final var notification = Notification.create();
        notification.append(validateCategories(categorias));
        notification.validate(() -> pote.atualizar(nome,descricao,isAtivo, categorias));

        if (notification.hasError()) {
            throw new NotificationException(
                    "Could not update Aggregate Pote %s".formatted(command.id()), notification
            );
        }
        return AtualizarPoteResponse.de(this.poteGateway.atualizar(pote));
    }

    private ValidationHandler validateCategories(List<CategoriaID> ids) {
        final var notification = Notification.create();
        if (ids == null || ids.isEmpty()) {
            return notification;
        }

        final var retrievedIds = categoriaGateway.existsByIds(ids);

        if (ids.size() != retrievedIds.size()) {
            final var missingIds = new ArrayList<>(ids);
            missingIds.removeAll(retrievedIds);

            final var missingIdsMessage = missingIds.stream()
                    .map(CategoriaID::getValue)
                    .collect(Collectors.joining(", "));

            notification.append(new Error("Some categories could not be found: %s".formatted(missingIdsMessage)));
        }

        return notification;
    }

    private Supplier<DomainException> notFound(final Identifier anId) {
        return () -> NotFoundException.with(Pote.class, anId);
    }

    private List<CategoriaID> toCategoriaID(final List<String> categorias) {
        return categorias.stream()
                .map(CategoriaID::de)
                .toList();
    }
}
