package br.com.i4business.money.infrastructure.despesa.persistence;

import br.com.i4business.money.domain.data.despesa.Despesa;
import br.com.i4business.money.domain.data.despesa.DespesaID;
import br.com.i4business.money.domain.data.despesa.DespesaItem;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

@Entity(name = "Despesa")
@Table(name = "despesa")
public class DespesaJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "despesa", cascade = ALL, fetch = EAGER, orphanRemoval = true)
    private Set<DespesaItemJpaEntity> itens;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @Column(name = "criado_em", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant criadoEm;

    @Column(name = "atualizado_em", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant atualizadoEm;

    @Column(name = "deletado_em", columnDefinition = "DATETIME(6)")
    private Instant deletadoEm;

    public DespesaJpaEntity() {
    }

    private DespesaJpaEntity(
       final String id,
       final String nome,
       final boolean ativo,
       final Instant criadoEm,
       final Instant atualizadoEm,
       final Instant deletadoEm
    ) {
        this.id = id;
        this.nome = nome;
        this.itens = new HashSet<>(3);
        this.ativo = ativo;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
        this.deletadoEm = deletadoEm;
    }

    public static DespesaJpaEntity toEntity(final Despesa despesa) {
        final var aDespesa =  new DespesaJpaEntity(
                despesa.getId().getValue(),
                despesa.getNome(),
                despesa.isAtivo(),
                despesa.getCriadoEm(),
                despesa.getAlteradoEm(),
                despesa.getDeletadoEm()
        );
        despesa.getItens().forEach(aDespesa::addItem);
        return aDespesa;
    }

    private void addItem(final DespesaItem despesaItem) {
        this.itens.add(DespesaItemJpaEntity.de(this, despesaItem.getItem(), despesaItem.getValor()));
    }

    public Despesa toAggregate() {
        Set<DespesaItem> aggregateItems = itens.stream()
                .map(DespesaItemJpaEntity::toAggregate)
                .collect(Collectors.toSet());

        return Despesa.com(
                DespesaID.de(getId()),
                getNome(),
                isAtivo(),
                getCriadoEm(),
                getAtualizadoEm(),
                getDeletadoEm(),
                aggregateItems.stream().toList()
        );
    }


    public String getId() {
        return id;
    }

    public DespesaJpaEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public DespesaJpaEntity setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public DespesaJpaEntity setAtivo(boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public DespesaJpaEntity setCriadoEm(Instant criadoEm) {
        this.criadoEm = criadoEm;
        return this;
    }

    public Instant getAtualizadoEm() {
        return atualizadoEm;
    }

    public DespesaJpaEntity setAtualizadoEm(Instant atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
        return this;
    }

    public Instant getDeletadoEm() {
        return deletadoEm;
    }

    public DespesaJpaEntity setDeletadoEm(Instant deletadoEm) {
        this.deletadoEm = deletadoEm;
        return this;
    }

    public Set<DespesaItemJpaEntity> getItens() {
        return itens;
    }

    public DespesaJpaEntity setItens(Set<DespesaItemJpaEntity> itens) {
        this.itens = itens;
        return this;
    }
}
