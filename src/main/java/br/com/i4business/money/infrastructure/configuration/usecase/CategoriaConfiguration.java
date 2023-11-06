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
import br.com.i4business.money.domain.data.categoria.CategoriaGateway;
import br.com.i4business.money.domain.data.item.ItemGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoriaConfiguration {

    private final CategoriaGateway categoryGateway;
    private final ItemGateway itemGateway;

    public CategoriaConfiguration(final CategoriaGateway categoryGateway, final ItemGateway itemGateway) {
        this.categoryGateway = categoryGateway;
        this.itemGateway = itemGateway;
    }

    @Bean
    public CadastrarCategoriaUseCase cadastrarCategoriaUseCase() {
        return new DefaultCadastrarCategoriaUseCase(categoryGateway, itemGateway);
    }

    @Bean
    public AtualizarCategoriaUseCase atualizarCategoriaUseCase(){
        return new DefaultAtualizarCategoriaUseCase(categoryGateway, itemGateway);
    }

    @Bean
    public DeletarCategoriaUseCase deletarCategoriaUseCase(){
        return new DefaultDeletarCategoriaUseCase(categoryGateway);
    }

    @Bean
    public GetCategoriaByIdUseCase getCategoriaByIdUseCase(){
        return new DefaultGetCategoriaByIdUseCase(categoryGateway);
    }
    @Bean
    public ListarCategoriasUseCase listarCategoriasUseCase(){
        return new DefaultListarCategoriasUseCase(categoryGateway);
    }
}
