package br.com.i4business.money.application.despesa.deletar;

import br.com.i4business.money.domain.data.categoria.CategoriaGateway;
import br.com.i4business.money.domain.data.categoria.CategoriaID;
import br.com.i4business.money.domain.data.despesa.DespesaGateway;
import br.com.i4business.money.domain.data.despesa.DespesaID;

public class DefaultDeletarDespesaUseCase extends DeletarDespesaUseCase {

    private final DespesaGateway despesaGateway;

    public DefaultDeletarDespesaUseCase(DespesaGateway despesaGateway) {
        this.despesaGateway = despesaGateway;
    }

    @Override
    public void execute(final String in) {
        this.despesaGateway.deleteById(DespesaID.de(in));
    }
}
