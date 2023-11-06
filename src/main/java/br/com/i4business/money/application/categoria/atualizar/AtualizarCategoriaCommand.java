package br.com.i4business.money.application.categoria.atualizar;

import java.util.List;

public record AtualizarCategoriaCommand(
        String id,
        String nome,
        String descricao,
        boolean isAtivo,

        List<String> itens
) {
    public static AtualizarCategoriaCommand com(
            final String id,
            final String nome,
            final String descricao,
            final boolean isAtivo,
            final List<String> itens
    ) {
        return new AtualizarCategoriaCommand(id, nome, descricao, isAtivo, itens);
    }
}
