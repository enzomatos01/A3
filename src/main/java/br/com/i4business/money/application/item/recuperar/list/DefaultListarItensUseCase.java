package br.com.i4business.money.application.item.recuperar.list;

import br.com.i4business.money.domain.data.item.ItemGateway;
import br.com.i4business.money.domain.pagination.Pagination;
import br.com.i4business.money.domain.pagination.SearchQuery;

import java.util.Objects;

public class DefaultListarItensUseCase extends ListarItensUseCase {

    private final ItemGateway itemGateway;

    public DefaultListarItensUseCase(ItemGateway itemGateway) {
        this.itemGateway = Objects.requireNonNull(itemGateway);
    }

    @Override
    public Pagination<ItemListOutput> execute(final SearchQuery searchQuery) {
        return this.itemGateway.findAll(searchQuery)
                .map(ItemListOutput::de);
    }
}
