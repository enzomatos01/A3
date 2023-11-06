package br.com.i4business.money.application.categoria.recuperar.list;

import br.com.i4business.money.domain.data.categoria.CategoriaGateway;
import br.com.i4business.money.domain.pagination.Pagination;
import br.com.i4business.money.domain.pagination.SearchQuery;

import java.util.Objects;

public class DefaultListarCategoriasUseCase extends ListarCategoriasUseCase{

    private final CategoriaGateway categoriaGateway;

    public DefaultListarCategoriasUseCase(CategoriaGateway categoriaGateway) {
        this.categoriaGateway = Objects.requireNonNull(categoriaGateway);
    }

    @Override
    public Pagination<CategoriaListResponse> execute(final SearchQuery searchQuery) {
        return this.categoriaGateway.findAll(searchQuery)
                .map(CategoriaListResponse::de);
    }
}
