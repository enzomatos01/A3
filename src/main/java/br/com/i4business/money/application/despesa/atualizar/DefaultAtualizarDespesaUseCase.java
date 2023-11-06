package br.com.i4business.money.application.despesa.atualizar;

import br.com.i4business.money.application.despesa.cadastrar.CadastrarDespesaItemCommand;
import br.com.i4business.money.domain.data.Identifier;
import br.com.i4business.money.domain.data.despesa.Despesa;
import br.com.i4business.money.domain.data.despesa.DespesaGateway;
import br.com.i4business.money.domain.data.despesa.DespesaID;
import br.com.i4business.money.domain.data.despesa.DespesaItem;
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

public class DefaultAtualizarDespesaUseCase extends AtualizarDespesaUseCase {

    private final DespesaGateway despesaGateway;
    private final ItemGateway itemGateway;

    public DefaultAtualizarDespesaUseCase(final DespesaGateway despesaGateway, ItemGateway itemGateway) {
        this.despesaGateway = Objects.requireNonNull(despesaGateway);
        this.itemGateway = Objects.requireNonNull(itemGateway);
    }

    @Override
    public AtualizarDespesaResponse execute(AtualizarDespesaCommand command) {
        final var id = DespesaID.de(command.id());
        final var mes = command.nome();
        final var itens = toDespesaItem(command.itens());
        final var isAtivo = command.isAtivo();

        final var despesa = this.despesaGateway.findById(id)
                .orElseThrow(notFound(id));

        final var notification = Notification.create();
        notification.append(validateItens(itens));
        notification.validate(() -> despesa.atualizar(mes,itens,isAtivo));

        if (notification.hasError()) {
            throw new NotificationException(
                    "Could not update Aggregate Despesa %s".formatted(command.id()), notification
            );
        }
        return AtualizarDespesaResponse.de(this.despesaGateway.atualizar(despesa));
    }

    private ValidationHandler validateItens(List<DespesaItem> itens) {
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

        return notification;
    }

    private Supplier<DomainException> notFound(final Identifier anId) {
        return () -> NotFoundException.with(Despesa.class, anId);
    }

    private List<DespesaItem> toDespesaItem(final List<AtualizarDespesaItemCommand> itens) {
        return itens.stream()
                .map(itemCommand -> new DespesaItem(new ItemID(itemCommand.id()), itemCommand.valor()))
                .collect(Collectors.toList());
    }
}
