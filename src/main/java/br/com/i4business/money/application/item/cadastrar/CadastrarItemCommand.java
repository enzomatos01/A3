package br.com.i4business.money.application.item.cadastrar;

public record CadastrarItemCommand(
        String nome,
        String descricao,
        boolean isAtivo
) {
    public static CadastrarItemCommand com(
            final String nome,
            final String descricao,
            final boolean isAtivo
            ) {
        return new CadastrarItemCommand(nome, descricao, isAtivo);
    }
}
