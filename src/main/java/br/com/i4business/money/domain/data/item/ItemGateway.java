package br.com.i4business.money.domain.data.item;

import br.com.i4business.money.domain.pagination.Pagination;
import br.com.i4business.money.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface ItemGateway {

    Item cadastrar(Item item);

    Item atualizar(Item item);

    public void deleteById(ItemID id);

    Optional<Item> findById(ItemID id);

    Pagination<Item> findAll(SearchQuery aQuery);

    List<ItemID> existsByIds(Iterable<ItemID> ids);


}
