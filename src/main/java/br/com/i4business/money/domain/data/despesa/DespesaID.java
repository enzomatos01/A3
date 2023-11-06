package br.com.i4business.money.domain.data.despesa;

import br.com.i4business.money.domain.data.Identifier;
import br.com.i4business.money.domain.util.IdUtils;

import java.util.Objects;

public class DespesaID extends Identifier {
    private final String value;

    public DespesaID(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static DespesaID unique() {
        return DespesaID.de(IdUtils.uuid());
    }

    public static DespesaID de(final String anId) {
        return new DespesaID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final DespesaID that = (DespesaID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
