package br.com.i4business.money.infrastructure.configuration.usecase;

import br.com.i4business.money.application.item.atualizar.AtualizarItemUseCase;
import br.com.i4business.money.application.item.atualizar.DefaultAtualizarItemUseCase;
import br.com.i4business.money.application.item.cadastrar.CadastrarItemUseCase;
import br.com.i4business.money.application.item.cadastrar.DefaultCadastrarItemUseCase;
import br.com.i4business.money.application.item.deletar.DefaultDeletarItemUseCase;
import br.com.i4business.money.application.item.deletar.DeletarItemUseCase;
import br.com.i4business.money.application.item.recuperar.get.DefaultGetItemByIdUseCase;
import br.com.i4business.money.application.item.recuperar.get.GetItemByIdUseCase;
import br.com.i4business.money.application.item.recuperar.list.DefaultListarItensUseCase;
import br.com.i4business.money.application.item.recuperar.list.ListarItensUseCase;
import br.com.i4business.money.domain.data.categoria.CategoriaGateway;
import br.com.i4business.money.domain.data.item.ItemGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemConfiguration {

    private final ItemGateway itemGateway;

    public ItemConfiguration(final ItemGateway itemGateway, final CategoriaGateway categoriaGateway) {
        this.itemGateway = itemGateway;
    }

    @Bean
    public CadastrarItemUseCase cadastrarItemUseCase() {
        return new DefaultCadastrarItemUseCase(itemGateway);
    }

    @Bean
    public AtualizarItemUseCase atualizarItemUseCase(){
        return new DefaultAtualizarItemUseCase(itemGateway);
    }

    @Bean
    public DeletarItemUseCase deletarItemUseCase(){
        return new DefaultDeletarItemUseCase(itemGateway);
    }

    @Bean
    public GetItemByIdUseCase getItemByIdUseCase(){
        return new DefaultGetItemByIdUseCase(itemGateway);
    }
    @Bean
    public ListarItensUseCase listarItensUseCase(){
        return new DefaultListarItensUseCase(itemGateway);
    }
}
