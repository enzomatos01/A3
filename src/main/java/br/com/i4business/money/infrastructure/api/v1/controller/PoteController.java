package br.com.i4business.money.infrastructure.api.v1.controller;

import br.com.i4business.money.application.pote.atualizar.AtualizarPoteCommand;
import br.com.i4business.money.application.pote.atualizar.AtualizarPoteUseCase;
import br.com.i4business.money.application.pote.cadastrar.CadastrarPoteCommand;
import br.com.i4business.money.application.pote.cadastrar.CadastrarPoteUseCase;
import br.com.i4business.money.application.pote.deletar.DeletarPoteUseCase;
import br.com.i4business.money.application.pote.recuperar.get.GetPoteByIdUseCase;
import br.com.i4business.money.application.pote.recuperar.list.ListarPotesUseCase;
import br.com.i4business.money.domain.pagination.Pagination;
import br.com.i4business.money.domain.pagination.SearchQuery;
import br.com.i4business.money.infrastructure.api.v1.PoteAPI;
import br.com.i4business.money.infrastructure.pote.model.AtualizarPoteRequest;
import br.com.i4business.money.infrastructure.pote.model.CadastrarPoteRequest;
import br.com.i4business.money.infrastructure.pote.model.ListarPotesResponse;
import br.com.i4business.money.infrastructure.pote.model.PoteResponse;
import br.com.i4business.money.infrastructure.pote.presenter.PoteApiPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class PoteController implements PoteAPI {

    private final CadastrarPoteUseCase cadastrarPoteUseCase;
    private final AtualizarPoteUseCase atualizarPoteUseCase;
    private final DeletarPoteUseCase deletePoteUseCase;
    private final GetPoteByIdUseCase getPoteByIdUseCase;

    private final ListarPotesUseCase listarPotesUseCase;

    public PoteController(
            final CadastrarPoteUseCase cadastrarPoteUseCase,
            final AtualizarPoteUseCase atualizarPoteUseCase,
            final DeletarPoteUseCase deletePoteUseCase,
            final GetPoteByIdUseCase getPoteByIdUseCase,
            final ListarPotesUseCase listarPotesUseCase
            ) {
        this.cadastrarPoteUseCase = Objects.requireNonNull(cadastrarPoteUseCase);
        this.atualizarPoteUseCase = Objects.requireNonNull(atualizarPoteUseCase);
        this.deletePoteUseCase = Objects.requireNonNull(deletePoteUseCase);
        this.getPoteByIdUseCase = Objects.requireNonNull(getPoteByIdUseCase);
        this.listarPotesUseCase = Objects.requireNonNull(listarPotesUseCase);
    }

    @Override
    public ResponseEntity<?> cadastrarPote(CadastrarPoteRequest input) {

        final var command = CadastrarPoteCommand.com(input.nome(), input.descricao(), input.ativo(), input.categorias());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.cadastrarPoteUseCase.execute(command));
    }

    @Override
    public ResponseEntity<?> atualizarPoteById(final String id, final AtualizarPoteRequest input) {
        final var command = AtualizarPoteCommand.com(
                id,
                input.nome(),
                input.descricao(),
                input.ativo() != null ? input.ativo() : true,
                input.categorias()
        );

        return ResponseEntity.status(HttpStatus.OK).body(this.atualizarPoteUseCase.execute(command));
    }

    @Override
    public void deleteById(final String id){
        this.deletePoteUseCase.execute (id);
    }

    public PoteResponse getById(@PathVariable(name = "id") String id){
        return PoteApiPresenter.present(this.getPoteByIdUseCase.execute(id));
    }

    @Override
    public Pagination<ListarPotesResponse> listarCategorias(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {
        return listarPotesUseCase.execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(PoteApiPresenter::present);
    }
}
