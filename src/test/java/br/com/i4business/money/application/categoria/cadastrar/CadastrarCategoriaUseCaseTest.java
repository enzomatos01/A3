package br.com.i4business.money.application.categoria.cadastrar;

import br.com.i4business.money.application.UseCaseTest;
import br.com.i4business.money.domain.data.categoria.CategoriaGateway;
import br.com.i4business.money.domain.data.item.ItemGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class CadastrarCategoriaUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCadastrarCategoriaUseCase useCase;

    @Mock
    private CategoriaGateway categoriaGateway;

    @Mock
    private ItemGateway itemGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(categoriaGateway, itemGateway);
    }

    // 1. Teste do caminho feliz
    // 2. Teste passando uma propriedade inválida (name)
    // 3. Teste criando uma categoria inativa
    // 4. Teste simulando um erro generico vindo do gateway

    @Test
    public void givenAValidCommand_whenCallsCreateCategory_shouldReturnCategoryId() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCommand =
                CadastrarCategoriaCommand.com(expectedName, expectedDescription, expectedIsActive, new ArrayList<>());

        when(categoriaGateway.cadastrar(any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand);

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(categoriaGateway, times(1)).cadastrar(argThat(aCategory ->
                Objects.equals(expectedName, aCategory.getNome())
                        && Objects.equals(expectedDescription, aCategory.getDescricao())
                        && Objects.equals(expectedIsActive, aCategory.isAtivo())
                        && Objects.nonNull(aCategory.getId())
                        && Objects.nonNull(aCategory.getCriadoEm())
                        && Objects.nonNull(aCategory.getAlteradoEm())
                        && Objects.isNull(aCategory.getDeletadoEm())
        ));
    }


}
