package br.com.i4business.money.infrastructure.api.v1.controller;

import br.com.i4business.money.application.item.atualizar.AtualizarItemCommand;
import br.com.i4business.money.application.item.atualizar.AtualizarItemUseCase;
import br.com.i4business.money.application.item.cadastrar.CadastrarItemCommand;
import br.com.i4business.money.application.item.cadastrar.CadastrarItemUseCase;
import br.com.i4business.money.application.item.deletar.DeletarItemUseCase;
import br.com.i4business.money.application.item.recuperar.get.GetItemByIdUseCase;
import br.com.i4business.money.application.item.recuperar.list.ListarItensUseCase;
import br.com.i4business.money.domain.pagination.Pagination;
import br.com.i4business.money.domain.pagination.SearchQuery;
import br.com.i4business.money.infrastructure.api.v1.ItemAPI;
import br.com.i4business.money.infrastructure.item.model.AtualizarItemRequest;
import br.com.i4business.money.infrastructure.item.model.CadastrarItemRequest;
import br.com.i4business.money.infrastructure.item.model.ItemResponse;
import br.com.i4business.money.infrastructure.item.model.ListarItensResponse;
import br.com.i4business.money.infrastructure.item.presenter.ItemApiPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class ItemController implements ItemAPI {

    private final CadastrarItemUseCase cadastrarItemUseCase;
    private final AtualizarItemUseCase atualizarItemUseCase;
    private final DeletarItemUseCase deleteItemUseCase;
    private final GetItemByIdUseCase getItemByIdUseCase;

    private final ListarItensUseCase listarItensUseCase;

    public ItemController(
            final CadastrarItemUseCase cadastrarItemUseCase,
            final AtualizarItemUseCase atualizarItemUseCase,
            final DeletarItemUseCase deleteItemUseCase,
            final GetItemByIdUseCase getItemByIdUseCase,
            final ListarItensUseCase listarItensUseCase
            ) {
        this.cadastrarItemUseCase = Objects.requireNonNull(cadastrarItemUseCase);
        this.atualizarItemUseCase = Objects.requireNonNull(atualizarItemUseCase);
        this.deleteItemUseCase = Objects.requireNonNull(deleteItemUseCase);
        this.getItemByIdUseCase = Objects.requireNonNull(getItemByIdUseCase);
        this.listarItensUseCase = Objects.requireNonNull(listarItensUseCase);
    }

    @Override
    public ResponseEntity<?> cadastrarItem(CadastrarItemRequest input) {

        final var command = CadastrarItemCommand.com(input.nome(), input.descricao(), input.ativo());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.cadastrarItemUseCase.execute(command));
    }

    @Override
    public ResponseEntity<?> atualizarItemById(final String id, final AtualizarItemRequest input) {
        final var command = AtualizarItemCommand.com(
                id,
                input.nome(),
                input.descricao(),
                input.ativo() != null ? input.ativo() : true
        );

        return ResponseEntity.status(HttpStatus.OK).body(this.atualizarItemUseCase.execute(command));
    }

    @Override
    public void deleteById(final String id){
        this.deleteItemUseCase.execute (id);
    }

    public ItemResponse getById(@PathVariable(name = "id") String id){
        return ItemApiPresenter.present(this.getItemByIdUseCase.execute(id));
    }
    @Override
    public Pagination<ListarItensResponse> listarItens(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {
        return listarItensUseCase.execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(ItemApiPresenter::present);
    }
}
