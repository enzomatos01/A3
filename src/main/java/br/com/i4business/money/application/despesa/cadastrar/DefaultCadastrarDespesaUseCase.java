package br.com.i4business.money.application.despesa.cadastrar;

import br.com.i4business.money.domain.data.despesa.Despesa;
import br.com.i4business.money.domain.data.despesa.DespesaGateway;
import br.com.i4business.money.domain.data.despesa.DespesaItem;
import br.com.i4business.money.domain.data.item.ItemGateway;
import br.com.i4business.money.domain.data.item.ItemID;
import br.com.i4business.money.domain.exception.NotificationException;
import br.com.i4business.money.domain.validation.Error;
import br.com.i4business.money.domain.validation.ValidationHandler;
import br.com.i4business.money.domain.validation.handler.Notification;
import br.com.i4business.money.infrastructure.despesa.model.ItemDespesaRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultCadastrarDespesaUseCase extends CadastrarDespesaUseCase {

    private final DespesaGateway despesaGateway;
    private final ItemGateway itemGateway;

    public DefaultCadastrarDespesaUseCase(final DespesaGateway despesaGateway, final ItemGateway itemGateway) {
        this.despesaGateway = Objects.requireNonNull(despesaGateway);
        this.itemGateway = Objects.requireNonNull(itemGateway);
    }

    @Override
    public CadastrarDespesaResponse execute(CadastrarDespesaCommand command) {
        final var itens = toDespesaItem(command.itens());

        final var notification = Notification.create();
        notification.append(validateItens(itens));

        final var despesa = notification.validate(() -> Despesa.newDespesa(command.nome(), command.isAtivo(), toDespesaItem(command.itens())));

        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate Despesa", notification);
        }

        despesa.addItens(itens);
        return CadastrarDespesaResponse.de(this.despesaGateway.cadastrar(despesa));
    }

    private ValidationHandler validateItens(final List<DespesaItem> itens) {
        final var notification = Notification.create();

        if (itens == null || itens.isEmpty()) {
            return notification;
        }

        // Extrair apenas as IDs dos itens para validação
        List<ItemID> ids = itens.stream()
                .map(DespesaItem::getItem)
                .collect(Collectors.toList());

        final var retrievedIds = itemGateway.existsByIds(ids);

        if (ids.size() != retrievedIds.size()) {
            final var missingIds = new ArrayList<>(ids);
            missingIds.removeAll(retrievedIds);

            final var missingIdsMessage = missingIds.stream()
                    .map(ItemID::getValue)
                    .collect(Collectors.joining(", "));

            notification.append(new Error("Some itens could not be found: %s".formatted(missingIdsMessage)));
        }

        for (DespesaItem item : itens) {
            if (item.getValor().compareTo(BigDecimal.ZERO) <= 0) {
                notification.append(new Error("Item with ID " + item.getItem().getValue() + " has invalid value."));
            }
        }

        return notification;
    }

    private List<DespesaItem> toDespesaItem(final List<CadastrarDespesaItemCommand> itens) {
        return itens.stream()
                .map(itemCommand -> new DespesaItem(new ItemID(itemCommand.id()), itemCommand.valor()))
                .collect(Collectors.toList());
    }
}
