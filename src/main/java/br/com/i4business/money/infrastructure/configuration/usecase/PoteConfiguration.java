package br.com.i4business.money.infrastructure.configuration.usecase;

import br.com.i4business.money.application.pote.atualizar.AtualizarPoteUseCase;
import br.com.i4business.money.application.pote.atualizar.DefaultAtualizarPoteUseCase;
import br.com.i4business.money.application.pote.cadastrar.CadastrarPoteUseCase;
import br.com.i4business.money.application.pote.cadastrar.DefaultCadastrarPoteUseCase;
import br.com.i4business.money.application.pote.deletar.DefaultDeletarPoteUseCase;
import br.com.i4business.money.application.pote.deletar.DeletarPoteUseCase;
import br.com.i4business.money.application.pote.recuperar.get.DefaultGetPoteByIdUseCase;
import br.com.i4business.money.application.pote.recuperar.get.GetPoteByIdUseCase;
import br.com.i4business.money.application.pote.recuperar.list.DefaultListarPotesUseCase;
import br.com.i4business.money.application.pote.recuperar.list.ListarPotesUseCase;
import br.com.i4business.money.domain.data.categoria.CategoriaGateway;
import br.com.i4business.money.domain.data.pote.PoteGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PoteConfiguration {

    private final PoteGateway poteGateway;
    private final CategoriaGateway categoriaGateway;

    public PoteConfiguration(final PoteGateway poteGateway, final CategoriaGateway categoriaGateway) {
        this.poteGateway = poteGateway;
        this.categoriaGateway = categoriaGateway;
    }

    @Bean
    public CadastrarPoteUseCase cadastrarPoteUseCase() {
        return new DefaultCadastrarPoteUseCase(poteGateway, categoriaGateway);
    }

    @Bean
    public AtualizarPoteUseCase atualizarPoteUseCase(){
        return new DefaultAtualizarPoteUseCase(poteGateway, categoriaGateway);
    }

    @Bean
    public DeletarPoteUseCase deletarPoteUseCase(){
        return new DefaultDeletarPoteUseCase(poteGateway);
    }

    @Bean
    public GetPoteByIdUseCase getPoteByIdUseCase(){
        return new DefaultGetPoteByIdUseCase(poteGateway);
    }

    @Bean
    public ListarPotesUseCase listarPotesUseCase(){
        return new DefaultListarPotesUseCase(poteGateway);
    }
}
