package br.com.i4business.money.application.despesa.cadastrar;

import java.math.BigDecimal;

public record CadastrarDespesaItemCommand(
        String id,
        BigDecimal valor
) {
}
