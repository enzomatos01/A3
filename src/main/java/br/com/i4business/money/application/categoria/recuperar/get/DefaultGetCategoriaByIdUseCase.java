package br.com.i4business.money.application.categoria.recuperar.get;

import br.com.i4business.money.domain.data.categoria.CategoriaGateway;
import br.com.i4business.money.domain.data.categoria.CategoriaID;

public class DefaultGetCategoriaByIdUseCase extends GetCategoriaByIdUseCase{

    private final CategoriaGateway categoriaGateway;

    public DefaultGetCategoriaByIdUseCase(final CategoriaGateway categoriaGateway) {
        this.categoriaGateway = categoriaGateway;
    }
    @Override
    public GetCategoriaByIdResponse execute(final String categoriaId) {
        return this.categoriaGateway.findById(CategoriaID.de(categoriaId))
                .map(GetCategoriaByIdResponse::de)
                .orElseThrow();
    }
}
