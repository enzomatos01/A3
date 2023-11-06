package br.com.i4business.money.application.despesa.atualizar;

import java.math.BigDecimal;

public record AtualizarDespesaItemCommand(
        String id,
        BigDecimal valor
) {
}
