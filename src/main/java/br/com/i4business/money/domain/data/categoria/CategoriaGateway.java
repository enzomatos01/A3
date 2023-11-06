package br.com.i4business.money.domain.data.categoria;

import br.com.i4business.money.domain.pagination.Pagination;
import br.com.i4business.money.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface CategoriaGateway {

    Categoria cadastrar(Categoria categoria);

    Categoria atualizar(Categoria categoria);

    public void deleteById(CategoriaID id);

    Optional<Categoria> findById(CategoriaID id);

    Pagination<Categoria> findAll(SearchQuery aQuery);

    List<CategoriaID> existsByIds(Iterable<CategoriaID> ids);

}
