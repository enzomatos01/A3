package br.com.i4business.money.application.item.atualizar;

public record AtualizarItemCommand(
        String id,
        String nome,
        String descricao,
        boolean isAtivo
) {
    public static AtualizarItemCommand com(
            final String id,
            final String nome,
            final String descricao,
            final boolean isAtivo
    ) {
        return new AtualizarItemCommand(id, nome, descricao, isAtivo);
    }
}
