package br.com.i4business.money.application.categoria.recuperar.list;

import br.com.i4business.money.application.UseCase;
import br.com.i4business.money.domain.pagination.Pagination;
import br.com.i4business.money.domain.pagination.SearchQuery;

public abstract class ListarCategoriasUseCase extends UseCase<SearchQuery, Pagination<CategoriaListResponse>> {
}
