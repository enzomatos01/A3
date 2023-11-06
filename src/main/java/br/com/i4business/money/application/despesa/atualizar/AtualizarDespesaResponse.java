package br.com.i4business.money.application.despesa.atualizar;

import br.com.i4business.money.domain.data.despesa.Despesa;

public record AtualizarDespesaResponse(
        String id
) {
    public static AtualizarDespesaResponse de(final String anId) {
        return new AtualizarDespesaResponse(anId);
    }

    public static AtualizarDespesaResponse de(final Despesa aDespesa) {
        return new AtualizarDespesaResponse(aDespesa.getId().getValue());
    }
}
