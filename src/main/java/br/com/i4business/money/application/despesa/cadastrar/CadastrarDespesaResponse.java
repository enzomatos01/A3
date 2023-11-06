package br.com.i4business.money.application.despesa.cadastrar;

import br.com.i4business.money.domain.data.despesa.Despesa;

public record CadastrarDespesaResponse(
        String id
) {
    public static CadastrarDespesaResponse de(final String anId) {
        return new CadastrarDespesaResponse(anId);
    }

    public static CadastrarDespesaResponse de(final Despesa aDespesa) {
        return new CadastrarDespesaResponse(aDespesa.getId().getValue());
    }
}
