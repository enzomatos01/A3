package br.com.i4business.money.application.categoria.cadastrar;

import java.util.List;

public record CadastrarCategoriaCommand(
        String nome,
        String descricao,
        boolean isAtivo,
        List<String> itens
) {
    public static CadastrarCategoriaCommand com(
            final String nome,
            final String descricao,
            final Boolean isAtivo,
            final List<String> itens
            ) {
        return new CadastrarCategoriaCommand(nome, descricao, isAtivo != null ? isAtivo : true, itens);
    }
}
