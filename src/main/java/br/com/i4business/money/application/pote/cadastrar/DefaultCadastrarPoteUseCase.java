package br.com.i4business.money.application.pote.cadastrar;

import br.com.i4business.money.domain.data.categoria.CategoriaGateway;
import br.com.i4business.money.domain.data.categoria.CategoriaID;
import br.com.i4business.money.domain.data.pote.Pote;
import br.com.i4business.money.domain.data.pote.PoteGateway;
import br.com.i4business.money.domain.exception.NotificationException;
import br.com.i4business.money.domain.validation.Error;
import br.com.i4business.money.domain.validation.ValidationHandler;
import br.com.i4business.money.domain.validation.handler.Notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultCadastrarPoteUseCase extends CadastrarPoteUseCase{

    private final PoteGateway poteGateway;
    private final CategoriaGateway categoriaGateway;

    public DefaultCadastrarPoteUseCase(final PoteGateway poteGateway, final CategoriaGateway categoriaGateway) {
        this.poteGateway = Objects.requireNonNull(poteGateway);
        this.categoriaGateway = Objects.requireNonNull(categoriaGateway);
    }

    @Override
    public CadastrarPoteResponse execute(CadastrarPoteCommand cadastrarPoteCommand) {
        final var categorias = toCategoriaID(cadastrarPoteCommand.categorias());

        final var notification = Notification.create();
        notification.append(validateCategorias(categorias));

        final var pote = notification.validate(() -> Pote.newPote(cadastrarPoteCommand.nome(), cadastrarPoteCommand.descricao(), cadastrarPoteCommand.isAtivo()));

        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate Pote", notification);
        }

        pote.addCategorias(categorias);
        return CadastrarPoteResponse.de(this.poteGateway.cadastrar(pote));
    }

    private ValidationHandler validateCategorias(final List<CategoriaID> ids) {
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

            notification.append(new Error("Some categorias could not be found: %s".formatted(missingIdsMessage)));
        }

        return notification;
    }

    private List<CategoriaID> toCategoriaID(final List<String> categorias) {
        return categorias.stream()
                .map(CategoriaID::de)
                .toList();
    }
}
