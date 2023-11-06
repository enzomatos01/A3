package br.com.i4business.money.application.categoria.cadastrar;

import br.com.i4business.money.domain.data.categoria.Categoria;
import br.com.i4business.money.domain.data.categoria.CategoriaGateway;
import br.com.i4business.money.domain.data.item.ItemGateway;
import br.com.i4business.money.domain.data.item.ItemID;
import br.com.i4business.money.domain.exception.NotificationException;
import br.com.i4business.money.domain.validation.Error;
import br.com.i4business.money.domain.validation.ValidationHandler;
import br.com.i4business.money.domain.validation.handler.Notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultCadastrarCategoriaUseCase extends CadastrarCategoriaUseCase{

    private final CategoriaGateway categoriaGateway;
    private final ItemGateway itemGateway;

    public DefaultCadastrarCategoriaUseCase(final CategoriaGateway categoriaGateway, final ItemGateway itemGateway) {
        this.categoriaGateway = Objects.requireNonNull(categoriaGateway);
        this.itemGateway = Objects.requireNonNull(itemGateway);
    }

    @Override
    public CadastrarCategoriaResponse execute(CadastrarCategoriaCommand command) {
        final var itens = toItemID(command.itens());

        final var notification = Notification.create();
        notification.append(validateItens(itens));

        final var categoria = notification.validate(() -> Categoria.newCategoria(command.nome(), command.descricao(), command.isAtivo(), toItemID(command.itens())));

        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate Pote", notification);
        }

        categoria.addItens(itens);
        return CadastrarCategoriaResponse.de(this.categoriaGateway.cadastrar(categoria));


    }

    private ValidationHandler validateItens(final List<ItemID> ids) {
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

            notification.append(new Error("Some categorias could not be found: %s".formatted(missingIdsMessage)));
        }

        return notification;
    }

    private List<ItemID> toItemID(final List<String> itens) {
        return itens.stream()
                .map(ItemID::de)
                .toList();
    }
}
