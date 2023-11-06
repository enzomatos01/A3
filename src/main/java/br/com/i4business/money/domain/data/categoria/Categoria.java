package br.com.i4business.money.domain.data.categoria;

import br.com.i4business.money.domain.data.AggregateRoot;
import br.com.i4business.money.domain.data.item.ItemID;
import br.com.i4business.money.domain.exception.NotificationException;
import br.com.i4business.money.domain.util.InstantUtils;
import br.com.i4business.money.domain.validation.ValidationHandler;
import br.com.i4business.money.domain.validation.handler.Notification;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Categoria extends AggregateRoot<CategoriaID> {

    private String nome;
    private String descricao;
    private boolean ativo;
    private Instant criadoEm;
    private Instant alteradoEm;
    private Instant deletadoEm;
    private List<ItemID> itens;

    public Categoria(
            final CategoriaID categoriaID,
            final String nome,
            final String descricao,
            final boolean ativo,
            final List<ItemID> itens,
            final Instant criadoEm,
            final Instant alteradoEm,
            final Instant deletadoEm
    ) {
        super(categoriaID);
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
        this.itens = itens;
        this.criadoEm = criadoEm;
        this.alteradoEm = alteradoEm;
        this.deletadoEm = deletadoEm;
//        selfValidate();
    }

    public static Categoria newCategoria(final String nome, final String descricao, final boolean isAtivo, final List<ItemID> itens) {
        final var id = CategoriaID.unique();
        var now = InstantUtils.now();
        final var deletadoEm = isAtivo ? null : now;
        return new Categoria(id, nome, descricao, isAtivo, new ArrayList<>(itens != null ? itens : Collections.emptyList()), now, now, deletadoEm);
    }

    public static Categoria com(
            final CategoriaID categoriaID,
            final String nome,
            final String descricao,
            final boolean ativo,
            final List<ItemID> itens,
            final Instant criadoEm,
            final Instant alteradoEm,
            final Instant deletadoEm
    ) {
        return new Categoria(
                categoriaID,
                nome,
                descricao,
                ativo,
                itens,
                criadoEm,
                alteradoEm,
                deletadoEm
        );
    }

    public static Categoria com(final Categoria categoria) {
        return com(
                categoria.getId(),
                categoria.nome,
                categoria.descricao,
                categoria.isAtivo(),
                new ArrayList<>(categoria.itens),
                categoria.criadoEm,
                categoria.alteradoEm,
                categoria.deletadoEm
        );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new CategoriaValidator(handler, this).validate();
    }

    public Categoria atualizar(final String nome, final String descricao, final boolean isAtivo, final List<ItemID> itens) {
        if (isAtivo) {
            ativar();
        } else {
            desativar();
        }
        this.nome = nome;
        this.descricao = descricao;
        this.alteradoEm = Instant.now();
        this.itens = new ArrayList<>(itens != null ? itens : Collections.emptyList());
        return this;
    }

    public Categoria ativar() {
        this.deletadoEm = null;
        this.ativo = true;
        this.alteradoEm = InstantUtils.now();
        return this;
    }

    public Categoria desativar() {
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

    public List<ItemID> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public Instant getAlteradoEm() {
        return alteradoEm;
    }

    public Instant getDeletadoEm() {
        return deletadoEm;
    }

    public Categoria addItem(final ItemID itemID) {
        if (itemID == null) {
            return this;
        }
        this.itens.add(itemID);
        this.alteradoEm = InstantUtils.now();
        return this;
    }

    public Categoria addItens(final List<ItemID> itens) {
        if (itens == null || itens.isEmpty()) {
            return this;
        }
        this.itens.addAll(itens);
        this.alteradoEm = InstantUtils.now();
        return this;
    }

    public Categoria removeItem(final ItemID itemID) {
        if (itemID == null) {
            return this;
        }
        this.itens.remove(itemID);
        this.alteradoEm = InstantUtils.now();
        return this;
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);

        if (notification.hasError()) {
            throw new NotificationException("Failed to create a Aggregate Categoria", notification);
        }
    }
}
