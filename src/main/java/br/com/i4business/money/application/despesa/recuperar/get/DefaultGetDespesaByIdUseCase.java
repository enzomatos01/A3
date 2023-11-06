package br.com.i4business.money.application.despesa.recuperar.get;

import br.com.i4business.money.domain.data.despesa.DespesaGateway;
import br.com.i4business.money.domain.data.despesa.DespesaID;

public class DefaultGetDespesaByIdUseCase extends GetDespesaByIdUseCase {

    private final DespesaGateway despesaGateway;

    public DefaultGetDespesaByIdUseCase(final DespesaGateway despesaGateway) {
        this.despesaGateway = despesaGateway;
    }
    @Override
    public GetDespesaByIdResponse execute(final String despesaId) {
        return this.despesaGateway.findById(DespesaID.de(despesaId))
                .map(GetDespesaByIdResponse::de)
                .orElseThrow();
    }
}
