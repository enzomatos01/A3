package br.com.i4business.money.application.item.recuperar.get;

import br.com.i4business.money.domain.data.item.ItemGateway;
import br.com.i4business.money.domain.data.item.ItemID;

public class DefaultGetItemByIdUseCase extends GetItemByIdUseCase {

    private final ItemGateway itemGateway;

    public DefaultGetItemByIdUseCase(final ItemGateway itemGateway) {
        this.itemGateway = itemGateway;
    }
    @Override
    public GetItemByIdResponse execute(final String itemId) {
        return this.itemGateway.findById(ItemID.de(itemId))
                .map(GetItemByIdResponse::de)
                .orElseThrow();
    }
}
