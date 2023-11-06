package br.com.i4business.money.domain.data.despesa;

import br.com.i4business.money.domain.data.AggregateRoot;
import br.com.i4business.money.domain.data.categoria.Categoria;
import br.com.i4business.money.domain.data.categoria.CategoriaID;
import br.com.i4business.money.domain.data.item.ItemID;
import br.com.i4business.money.domain.exception.NotificationException;
import br.com.i4business.money.domain.util.InstantUtils;
import br.com.i4business.money.domain.validation.ValidationHandler;
import br.com.i4business.money.domain.validation.handler.Notification;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Despesa extends AggregateRoot<DespesaID> {

    private String nome;
    private boolean ativo;
    private Instant criadoEm;
    private Instant alteradoEm;
    private Instant deletadoEm;
    private List<DespesaItem> itens;

    public Despesa(DespesaID despesaID, String nome, boolean ativo, Instant criadoEm, Instant alteradoEm, Instant deletadoEm, List<DespesaItem> itens) {
        super(despesaID);
        this.nome = nome;
        this.ativo = ativo;
        this.criadoEm = criadoEm;
        this.alteradoEm = alteradoEm;
        this.deletadoEm = deletadoEm;
        this.itens = itens;
        selfValidate();
    }

    public String getNome() {
        return nome;
    }
    public List<DespesaItem> getItens() {
        return Collections.unmodifiableList(itens);
    }
    public boolean isAtivo() {
        return ativo;
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

    public Despesa addItem(final DespesaItem item) {
        if (item == null) {
            return this;
        }
        this.itens.add(item);
        this.alteradoEm = InstantUtils.now();
        return this;
    }

    public Despesa addItens(final List<DespesaItem> itens) {
        if (itens == null || itens.isEmpty()) {
            return this;
        }
        this.itens.addAll(itens);
        this.alteradoEm = InstantUtils.now();
        return this;
    }

    public Despesa atualizar(final String nome, final List<DespesaItem> itens, final boolean isAtivo) {
        if (isAtivo) {
            ativar();
        } else {
            desativar();
        }
        this.nome = nome;
        this.itens = itens;
        this.alteradoEm = Instant.now();
        return this;
    }

    public static Despesa newDespesa(final String nome, final boolean isAtivo, final List<DespesaItem> itens) {
        final var id = DespesaID.unique();
        var now = InstantUtils.now();
        final var deletadoEm = isAtivo ? null : now;
        return new Despesa(id, nome, isAtivo, now, now, deletadoEm, new ArrayList<>(itens != null ? itens : Collections.emptyList()));
    }

    public static Despesa com(
            final DespesaID despesaID,
            final String nome,
            final boolean ativo,
            final Instant criadoEm,
            final Instant alteradoEm,
            final Instant deletadoEm,
            final List<DespesaItem> itens
            ) {
        return new Despesa(
                despesaID,
                nome,
                ativo,
                criadoEm,
                alteradoEm,
                deletadoEm,
                itens
        );
    }

    public static Despesa com(final Despesa despesa) {
        return com(
                despesa.getId(),
                despesa.nome,
                despesa.isAtivo(),
                despesa.criadoEm,
                despesa.alteradoEm,
                despesa.deletadoEm,
                new ArrayList<>(despesa.itens)
        );
    }

    public Despesa ativar() {
        this.deletadoEm = null;
        this.ativo = true;
        this.alteradoEm = InstantUtils.now();
        return this;
    }

    public Despesa desativar() {
        if (getDeletadoEm() == null) {
            this.deletadoEm = InstantUtils.now();
        }

        this.ativo = false;
        this.alteradoEm = InstantUtils.now();
        return this;
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);

        if (notification.hasError()) {
            throw new NotificationException("Failed to create a Aggregate Despesa", notification);
        }
    }

    @Override
    public void validate(ValidationHandler handler) {
        new DespesaValidator(handler, this).validate();
    }
}
