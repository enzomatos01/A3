package br.com.i4business.money.application.despesa.cadastrar;

import br.com.i4business.money.infrastructure.despesa.model.ItemDespesaRequest;

import java.util.List;

public record CadastrarDespesaCommand(
        String nome,
        List<CadastrarDespesaItemCommand> itens,
        boolean isAtivo
) {
    public static CadastrarDespesaCommand com(
            final String nome,
            final List<CadastrarDespesaItemCommand> itens,
            final Boolean isAtivo
            ) {
        return new CadastrarDespesaCommand(nome, itens, isAtivo != null ? isAtivo : true);
    }
}
