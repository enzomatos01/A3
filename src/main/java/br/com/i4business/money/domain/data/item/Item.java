package br.com.i4business.money.domain.data.item;

import br.com.i4business.money.domain.data.AggregateRoot;
import br.com.i4business.money.domain.util.InstantUtils;
import br.com.i4business.money.domain.validation.ValidationHandler;

import java.time.Instant;


public class Item extends AggregateRoot<ItemID> {

    private String nome;
    private String descricao;
    private boolean ativo;
    private Instant criadoEm;
    private Instant alteradoEm;
    private Instant deletadoEm;
//    private CategoriaID categoria;

    public Item(ItemID itemID, String nome, String descricao, boolean ativo, Instant criadoEm, Instant alteradoEm, Instant deletadoEm) {
        super(itemID);
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
        this.criadoEm = criadoEm;
        this.alteradoEm = alteradoEm;
        this.deletadoEm = deletadoEm;
    }

    public static Item newItem(final String nome, final String descricao, final boolean isAtivo) {
        final var id = ItemID.unique();
        var now = InstantUtils.now();
        final var deletadoEm = isAtivo ? null : now;
        return new Item(id, nome, descricao, isAtivo, now, now, deletadoEm);
    }

    public static Item com(
            final ItemID id,
            final String nome,
            final String descricao,
            final boolean ativo,
            final Instant criadoEm,
            final Instant alteradoEm,
            final Instant deletadoEm
    ) {
        return new Item(
                id,
                nome,
                descricao,
                ativo,
                criadoEm,
                alteradoEm,
                deletadoEm
        );
    }

    public static Item com(final Item item) {
        return com(
                item.getId(),
                item.nome,
                item.descricao,
                item.isAtivo(),
                item.criadoEm,
                item.alteradoEm,
                item.deletadoEm
        );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new ItemValidator(handler, this).validate();
    }

    public Item atualizar(final String nome, final String descricao, final boolean isAtivo) {
        if (isAtivo) {
            ativar();
        } else {
            desativar();
        }
        this.nome = nome;
        this.descricao = descricao;
        this.alteradoEm = Instant.now();
        return this;
    }

    public Item ativar() {
        this.deletadoEm = null;
        this.ativo = true;
        this.alteradoEm = InstantUtils.now();
        return this;
    }

    public Item desativar() {
        if (getDeletadoEm() == null) {
            this.deletadoEm = InstantUtils.now();
        }

        this.ativo = false;
        this.alteradoEm = InstantUtils.now();
        return this;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

//    public CategoriaID getCategoria() {
//        return categoria;
//    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public Instant getAlteradoEm() {
        return alteradoEm;
    }

    public Instant getDeletadoEm() {
        return deletadoEm;
    }
}
