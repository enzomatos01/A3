package br.com.i4business.money.application.item.deletar;

import br.com.i4business.money.domain.data.item.ItemGateway;
import br.com.i4business.money.domain.data.item.ItemID;

public class DefaultDeletarItemUseCase extends DeletarItemUseCase {

    private final ItemGateway itemGateway;

    public DefaultDeletarItemUseCase(ItemGateway itemGateway) {
        this.itemGateway = itemGateway;
    }

    @Override
    public void execute(final String in) {
        this.itemGateway.deleteById(ItemID.de(in));
    }
}
