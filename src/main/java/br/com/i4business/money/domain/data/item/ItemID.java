package br.com.i4business.money.domain.data.item;

import br.com.i4business.money.domain.data.Identifier;
import br.com.i4business.money.domain.util.IdUtils;

import java.util.Objects;

public class ItemID extends Identifier {
    private final String value;

    public ItemID(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static ItemID unique() {
        return ItemID.de(IdUtils.uuid());
    }

    public static ItemID de(final String anId) {
        return new ItemID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ItemID that = (ItemID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
