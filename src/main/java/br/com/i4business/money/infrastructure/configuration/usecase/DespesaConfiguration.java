package br.com.i4business.money.infrastructure.configuration.usecase;

import br.com.i4business.money.application.categoria.atualizar.AtualizarCategoriaUseCase;
import br.com.i4business.money.application.categoria.atualizar.DefaultAtualizarCategoriaUseCase;
import br.com.i4business.money.application.categoria.cadastrar.CadastrarCategoriaUseCase;
import br.com.i4business.money.application.categoria.cadastrar.DefaultCadastrarCategoriaUseCase;
import br.com.i4business.money.application.categoria.deletar.DefaultDeletarCategoriaUseCase;
import br.com.i4business.money.application.categoria.deletar.DeletarCategoriaUseCase;
import br.com.i4business.money.application.categoria.recuperar.get.DefaultGetCategoriaByIdUseCase;
import br.com.i4business.money.application.categoria.recuperar.get.GetCategoriaByIdUseCase;
import br.com.i4business.money.application.categoria.recuperar.list.DefaultListarCategoriasUseCase;
import br.com.i4business.money.application.categoria.recuperar.list.ListarCategoriasUseCase;
import br.com.i4business.money.application.despesa.atualizar.AtualizarDespesaUseCase;
import br.com.i4business.money.application.despesa.atualizar.DefaultAtualizarDespesaUseCase;
import br.com.i4business.money.application.despesa.cadastrar.CadastrarDespesaUseCase;
import br.com.i4business.money.application.despesa.cadastrar.DefaultCadastrarDespesaUseCase;
import br.com.i4business.money.application.despesa.deletar.DefaultDeletarDespesaUseCase;
import br.com.i4business.money.application.despesa.deletar.DeletarDespesaUseCase;
import br.com.i4business.money.application.despesa.recuperar.get.DefaultGetDespesaByIdUseCase;
import br.com.i4business.money.application.despesa.recuperar.get.GetDespesaByIdUseCase;
import br.com.i4business.money.application.despesa.recuperar.list.DefaultListarDespesasUseCase;
import br.com.i4business.money.application.despesa.recuperar.list.ListarDespesasUseCase;
import br.com.i4business.money.domain.data.categoria.CategoriaGateway;
import br.com.i4business.money.domain.data.despesa.DespesaGateway;
import br.com.i4business.money.domain.data.item.ItemGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DespesaConfiguration {

    private final DespesaGateway despesaGateway;
    private final ItemGateway itemGateway;

    public DespesaConfiguration(final DespesaGateway despesaGateway, final ItemGateway itemGateway) {
        this.despesaGateway = despesaGateway;
        this.itemGateway = itemGateway;
    }

    @Bean
    public CadastrarDespesaUseCase cadastrarDespesaUseCase() {
        return new DefaultCadastrarDespesaUseCase(despesaGateway, itemGateway);
    }

    @Bean
    public AtualizarDespesaUseCase atualizarDespesaUseCase(){
        return new DefaultAtualizarDespesaUseCase(despesaGateway, itemGateway);
    }

    @Bean
    public DeletarDespesaUseCase deletarDespesaUseCase(){
        return new DefaultDeletarDespesaUseCase(despesaGateway);
    }

    @Bean
    public GetDespesaByIdUseCase getDespesaByIdUseCase(){
        return new DefaultGetDespesaByIdUseCase(despesaGateway);
    }
    @Bean
    public ListarDespesasUseCase listarDespesasUseCase(){
        return new DefaultListarDespesasUseCase(despesaGateway);
    }
}
