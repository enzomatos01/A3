package br.com.i4business.money.application.item.cadastrar;

import br.com.i4business.money.domain.data.item.Item;
import br.com.i4business.money.domain.data.item.ItemGateway;

import java.util.Objects;

public class DefaultCadastrarItemUseCase extends CadastrarItemUseCase {

    private final ItemGateway itemGateway;

    public DefaultCadastrarItemUseCase(final ItemGateway itemGateway) {
        this.itemGateway = Objects.requireNonNull(itemGateway);
    }

    @Override
    public CadastrarItemResponse execute(CadastrarItemCommand command) {
        final var item = Item.newItem(command.nome(), command.descricao(), command.isAtivo());
        return CadastrarItemResponse.de(this.itemGateway.cadastrar(item));
    }
}
