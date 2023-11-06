package br.com.i4business.money.application.categoria.atualizar;

import br.com.i4business.money.domain.data.Identifier;
import br.com.i4business.money.domain.data.categoria.CategoriaGateway;
import br.com.i4business.money.domain.data.categoria.CategoriaID;
import br.com.i4business.money.domain.data.item.ItemGateway;
import br.com.i4business.money.domain.data.item.ItemID;
import br.com.i4business.money.domain.data.pote.Pote;
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

public class DefaultAtualizarCategoriaUseCase extends AtualizarCategoriaUseCase {

    private final CategoriaGateway categoriaGateway;
    private final ItemGateway itemGateway;

    public DefaultAtualizarCategoriaUseCase(final CategoriaGateway categoriaGateway, ItemGateway itemGateway) {
        this.categoriaGateway = Objects.requireNonNull(categoriaGateway);
        this.itemGateway = Objects.requireNonNull(itemGateway);
    }

    @Override
    public AtualizarCategoriaResponse execute(AtualizarCategoriaCommand command) {
        final var id = CategoriaID.de(command.id());
        final var nome = command.nome();
        final var descricao = command.descricao();
        final var isAtivo = command.isAtivo();
        final var itens = toItemID(command.itens());

        final var categoria = this.categoriaGateway.findById(id)
                .orElseThrow(notFound(id));

        final var notification = Notification.create();
        notification.append(validateItens(itens));
        notification.validate(() -> categoria.atualizar(nome,descricao,isAtivo, itens));

        if (notification.hasError()) {
            throw new NotificationException(
                    "Could not update Aggregate Categoria %s".formatted(command.id()), notification
            );
        }
        return AtualizarCategoriaResponse.de(this.categoriaGateway.atualizar(categoria));
    }

    private ValidationHandler validateItens(List<ItemID> ids) {
        final var notification = Notification.create();
        if (ids == null || ids.isEmpty()) {
            return notification;
        }

        final var retrievedIds = itemGateway.existsByIds(ids);

        if (ids.size() != retrievedIds.size()) {
            final var missingIds = new ArrayList<>(ids);
            missingIds.removeAll(retrievedIds);

            final var missingIdsMessage = missingIds.stream()
                    .map(ItemID::getValue)
                    .collect(Collectors.joining(", "));

            notification.append(new Error("Some itens could not be found: %s".formatted(missingIdsMessage)));
        }

        return notification;
    }

    private Supplier<DomainException> notFound(final Identifier anId) {
        return () -> NotFoundException.with(Pote.class, anId);
    }

    private List<ItemID> toItemID(final List<String> itens) {
        return itens.stream()
                .map(ItemID::de)
                .toList();
    }
}
