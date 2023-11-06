package br.com.i4business.money.application.despesa.atualizar;

import br.com.i4business.money.application.despesa.cadastrar.CadastrarDespesaItemCommand;

import java.math.BigDecimal;
import java.util.List;

public record AtualizarDespesaCommand(
        String id,
        String nome,
        List<AtualizarDespesaItemCommand> itens,
        boolean isAtivo
) {
    public static AtualizarDespesaCommand com(
            final String id,
            final String nome,
            final List<AtualizarDespesaItemCommand> itens,
            final boolean isAtivo
    ) {
        return new AtualizarDespesaCommand(id, nome, itens, isAtivo);
    }
}
