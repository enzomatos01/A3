package br.com.i4business.money.application.pote.recuperar.list;

import br.com.i4business.money.application.UseCase;
import br.com.i4business.money.domain.pagination.Pagination;
import br.com.i4business.money.domain.pagination.SearchQuery;

public abstract class ListarPotesUseCase extends UseCase<SearchQuery, Pagination<PoteListResponse>> {
}
