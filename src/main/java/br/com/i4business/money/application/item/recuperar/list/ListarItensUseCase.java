package br.com.i4business.money.application.item.recuperar.list;

import br.com.i4business.money.application.UseCase;
import br.com.i4business.money.domain.pagination.Pagination;
import br.com.i4business.money.domain.pagination.SearchQuery;

public abstract class ListarItensUseCase extends UseCase<SearchQuery, Pagination<ItemListOutput>> {
}
