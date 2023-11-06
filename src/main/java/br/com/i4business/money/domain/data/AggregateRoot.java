package br.com.i4business.money.domain.data;

public abstract class AggregateRoot <ID extends Identifier> extends Entity<ID>{
    protected AggregateRoot(ID id) {
        super(id);
    }
}
