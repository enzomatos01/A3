package br.com.i4business.money.domain.data.pote;

import br.com.i4business.money.domain.data.Identifier;
import br.com.i4business.money.domain.util.IdUtils;

import java.util.Objects;

public class PoteID extends Identifier {
    private final String value;

    public PoteID(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static PoteID unique() {
        return PoteID.de(IdUtils.uuid());
    }

    public static PoteID de(final String anId) {
        return new PoteID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PoteID that = (PoteID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
