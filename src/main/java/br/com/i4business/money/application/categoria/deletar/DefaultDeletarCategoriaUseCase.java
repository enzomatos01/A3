package br.com.i4business.money.application.categoria.deletar;

import br.com.i4business.money.domain.data.categoria.CategoriaGateway;
import br.com.i4business.money.domain.data.categoria.CategoriaID;

public class DefaultDeletarCategoriaUseCase extends DeletarCategoriaUseCase {

    private final CategoriaGateway categoriaGateway;

    public DefaultDeletarCategoriaUseCase(CategoriaGateway categoriaGateway) {
        this.categoriaGateway = categoriaGateway;
    }

    @Override
    public void execute(final String in) {
        this.categoriaGateway.deleteById(CategoriaID.de(in));
    }
}
