package br.com.i4business.money.domain.data.categoria;

import br.com.i4business.money.domain.data.Identifier;
import br.com.i4business.money.domain.util.IdUtils;

import java.util.Objects;

public class CategoriaID extends Identifier {
    private final String value;

    public CategoriaID(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static CategoriaID unique() {
        return CategoriaID.de(IdUtils.uuid());
    }

    public static CategoriaID de(final String anId) {
        return new CategoriaID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CategoriaID that = (CategoriaID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
