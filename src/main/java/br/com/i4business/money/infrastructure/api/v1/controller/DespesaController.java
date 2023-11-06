package br.com.i4business.money.infrastructure.api.v1.controller;

import br.com.i4business.money.application.despesa.atualizar.AtualizarDespesaCommand;
import br.com.i4business.money.application.despesa.atualizar.AtualizarDespesaItemCommand;
import br.com.i4business.money.application.despesa.atualizar.AtualizarDespesaUseCase;
import br.com.i4business.money.application.despesa.cadastrar.CadastrarDespesaCommand;
import br.com.i4business.money.application.despesa.cadastrar.CadastrarDespesaItemCommand;
import br.com.i4business.money.application.despesa.cadastrar.CadastrarDespesaUseCase;
import br.com.i4business.money.application.despesa.deletar.DeletarDespesaUseCase;
import br.com.i4business.money.application.despesa.recuperar.get.GetDespesaByIdUseCase;
import br.com.i4business.money.application.despesa.recuperar.list.ListarDespesasUseCase;
import br.com.i4business.money.domain.pagination.Pagination;
import br.com.i4business.money.domain.pagination.SearchQuery;
import br.com.i4business.money.infrastructure.api.v1.DespesaAPI;
import br.com.i4business.money.infrastructure.despesa.model.*;
import br.com.i4business.money.infrastructure.despesa.presenter.DespesaApiPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class DespesaController implements DespesaAPI {

    private final CadastrarDespesaUseCase cadastrarDespesaUseCase;
    private final AtualizarDespesaUseCase atualizarDespesaUseCase;
    private final DeletarDespesaUseCase deleteDespesaUseCase;
    private final GetDespesaByIdUseCase getDespesaByIdUseCase;
    private final ListarDespesasUseCase listarDespesasUseCase;

    public DespesaController(
            final CadastrarDespesaUseCase cadastrarDespesaUseCase,
            final AtualizarDespesaUseCase atualizarDespesaUseCase,
            final DeletarDespesaUseCase deleteDespesaUseCase,
            final GetDespesaByIdUseCase getDespesaByIdUseCase,
            final ListarDespesasUseCase listarDespesasUseCase
            ) {
        this.cadastrarDespesaUseCase = Objects.requireNonNull(cadastrarDespesaUseCase);
        this.atualizarDespesaUseCase = Objects.requireNonNull(atualizarDespesaUseCase);
        this.deleteDespesaUseCase = Objects.requireNonNull(deleteDespesaUseCase);
        this.getDespesaByIdUseCase = Objects.requireNonNull(getDespesaByIdUseCase);
        this.listarDespesasUseCase = Objects.requireNonNull(listarDespesasUseCase);
    }

    @Override
    public ResponseEntity<?> cadastrarDespesa(CadastrarDespesaRequest input) {

        List<CadastrarDespesaItemCommand> itens = convert(input.itens(),
                                                  item -> new CadastrarDespesaItemCommand(item.id(), item.valor()));

        final var command = CadastrarDespesaCommand.com(input.nome(), itens, input.ativo());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.cadastrarDespesaUseCase.execute(command));
    }

    @Override
    public ResponseEntity<?> atualizarDespesaById(final String id, final AtualizarDespesaRequest input) {

        List<AtualizarDespesaItemCommand> itens = convert(input.itens(),
                item -> new AtualizarDespesaItemCommand(item.id(), item.valor()));

        final var command = AtualizarDespesaCommand.com(
                id,
                input.nome(),
                itens,
                input.ativo() != null ? input.ativo() : true
        );

        return ResponseEntity.status(HttpStatus.OK).body(this.atualizarDespesaUseCase.execute(command));
    }

    @Override
    public void deleteById(final String id){
        this.deleteDespesaUseCase.execute (id);
    }

    public DespesaResponse getById(@PathVariable(name = "id") String id){
        return DespesaApiPresenter.present(this.getDespesaByIdUseCase.execute(id));
    }
    @Override
    public Pagination<ListarDespesasResponse> listarDespesas(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {
        return listarDespesasUseCase.execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(DespesaApiPresenter::present);
    }

    public static <S, T> List<T> convert(List<S> sourceList, Function<S, T> converterFunction) {
        return sourceList.stream()
                .map(converterFunction)
                .collect(Collectors.toList());
    }

}
