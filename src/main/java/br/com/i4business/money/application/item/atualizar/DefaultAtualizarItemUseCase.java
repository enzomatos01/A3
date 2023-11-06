package br.com.i4business.money.application.item.atualizar;

import br.com.i4business.money.domain.data.item.ItemGateway;
import br.com.i4business.money.domain.data.item.ItemID;

import java.util.Objects;

public class DefaultAtualizarItemUseCase extends AtualizarItemUseCase {

    private final ItemGateway itemGateway;

    public DefaultAtualizarItemUseCase(final ItemGateway itemGateway) {
        this.itemGateway = Objects.requireNonNull(itemGateway);
    }

    @Override
    public AtualizarItemResponse execute(AtualizarItemCommand command) {
        final var aItem = this.itemGateway.findById(ItemID.de(command.id())).orElseThrow();
        aItem.atualizar(command.nome(), command.descricao(), command.isAtivo());
        return AtualizarItemResponse.de(this.itemGateway.atualizar(aItem));
    }
}
