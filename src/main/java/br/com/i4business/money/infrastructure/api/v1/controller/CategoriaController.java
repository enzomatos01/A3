package br.com.i4business.money.infrastructure.api.v1.controller;

import br.com.i4business.money.application.categoria.atualizar.AtualizarCategoriaCommand;
import br.com.i4business.money.application.categoria.atualizar.AtualizarCategoriaUseCase;
import br.com.i4business.money.application.categoria.cadastrar.CadastrarCategoriaCommand;
import br.com.i4business.money.application.categoria.cadastrar.CadastrarCategoriaUseCase;
import br.com.i4business.money.application.categoria.deletar.DeletarCategoriaUseCase;
import br.com.i4business.money.application.categoria.recuperar.get.GetCategoriaByIdUseCase;
import br.com.i4business.money.application.categoria.recuperar.list.ListarCategoriasUseCase;
import br.com.i4business.money.domain.pagination.Pagination;
import br.com.i4business.money.domain.pagination.SearchQuery;
import br.com.i4business.money.infrastructure.api.v1.CategoriaAPI;
import br.com.i4business.money.infrastructure.categoria.model.AtualizarCategoriaRequest;
import br.com.i4business.money.infrastructure.categoria.model.CadastrarCategoriaRequest;
import br.com.i4business.money.infrastructure.categoria.model.CategoriaResponse;
import br.com.i4business.money.infrastructure.categoria.model.ListarCategoriasResponse;
import br.com.i4business.money.infrastructure.categoria.presenter.CategoriaApiPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class CategoriaController implements CategoriaAPI {

    private final CadastrarCategoriaUseCase cadastrarCategoriaUseCase;
    private final AtualizarCategoriaUseCase atualizarCategoriaUseCase;
    private final DeletarCategoriaUseCase deleteCategoriaUseCase;
    private final GetCategoriaByIdUseCase getCategoriaByIdUseCase;
    private final ListarCategoriasUseCase listarCategoriasUseCase;

    public CategoriaController(
            final CadastrarCategoriaUseCase cadastrarCategoriaUseCase,
            final AtualizarCategoriaUseCase atualizarCategoriaUseCase,
            final DeletarCategoriaUseCase deleteCategoriaUseCase,
            final GetCategoriaByIdUseCase getCategoriaByIdUseCase,
            final ListarCategoriasUseCase listarCategoriasUseCase
            ) {
        this.cadastrarCategoriaUseCase = Objects.requireNonNull(cadastrarCategoriaUseCase);
        this.atualizarCategoriaUseCase = Objects.requireNonNull(atualizarCategoriaUseCase);
        this.deleteCategoriaUseCase = Objects.requireNonNull(deleteCategoriaUseCase);
        this.getCategoriaByIdUseCase = Objects.requireNonNull(getCategoriaByIdUseCase);
        this.listarCategoriasUseCase = Objects.requireNonNull(listarCategoriasUseCase);
    }

    @Override
    public ResponseEntity<?> cadastrarCategoria(CadastrarCategoriaRequest input) {

        final var command = CadastrarCategoriaCommand.com(input.nome(), input.descricao(), input.ativo(), input.itens());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.cadastrarCategoriaUseCase.execute(command));
    }

    @Override
    public ResponseEntity<?> atualizarCategoriaById(final String id, final AtualizarCategoriaRequest input) {
        final var command = AtualizarCategoriaCommand.com(
                id,
                input.nome(),
                input.descricao(),
                input.ativo() != null ? input.ativo() : true,
                input.itens()
        );

        return ResponseEntity.status(HttpStatus.OK).body(this.atualizarCategoriaUseCase.execute(command));
    }

    @Override
    public void deleteById(final String id){
        this.deleteCategoriaUseCase.execute (id);
    }

    public CategoriaResponse getById(@PathVariable(name = "id") String id){
        return CategoriaApiPresenter.present(this.getCategoriaByIdUseCase.execute(id));
    }
    @Override
    public Pagination<ListarCategoriasResponse> listarCategorias(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {
        return listarCategoriasUseCase.execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(CategoriaApiPresenter::present);
    }
}
