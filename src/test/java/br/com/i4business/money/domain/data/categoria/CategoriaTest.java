package br.com.i4business.money.domain.data.categoria;

import br.com.i4business.money.domain.UnitTest;
import br.com.i4business.money.domain.data.item.ItemID;
import br.com.i4business.money.domain.exception.DomainException;
import br.com.i4business.money.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CategoriaTest extends UnitTest {

    @Test
    public void dadoParametrosValidos_quandoChamarNovaCategoria_entaoInstancieACategoria() {
        final var expectedName = "Moradia";
        final var expectedDescription = "A categoria mais usada";
        final var expectedIsActive = true;
        final List<ItemID> expectedItens = null;

        final var actualCategory =
                Categoria.newCategoria(expectedName, expectedDescription, expectedIsActive,expectedItens);

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getNome());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescricao());
        Assertions.assertEquals(expectedIsActive, actualCategory.isAtivo());
        Assertions.assertNotNull(actualCategory.getCriadoEm());
        Assertions.assertNotNull(actualCategory.getAlteradoEm());
        Assertions.assertNull(actualCategory.getDeletadoEm());
    }

    @Test
    public void dadoNomeNull_quandoChamarNovaCategoriaEValidar_entaoDeveSerLancadoUmError() {
        final String expectedName = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'nome' should not be null";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var actualCategory =
                Categoria.newCategoria(expectedName, expectedDescription, expectedIsActive, new ArrayList<>());

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void dadoNomeVazio_quandoChamarNovaCategoriaEValidar_entaoDeveSerLancadoUmError() {
        final var expectedName = "  ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'nome' should not be empty";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var actualCategory =
                Categoria.newCategoria(expectedName, expectedDescription, expectedIsActive, new ArrayList<>());

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }


}
