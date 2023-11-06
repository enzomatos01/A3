package br.com.i4business.money.domain.data.item;

import br.com.i4business.money.domain.validation.Error;
import br.com.i4business.money.domain.validation.ValidationHandler;
import br.com.i4business.money.domain.validation.Validator;

public class ItemValidator extends Validator {

    public static final int NAME_MAX_LENGTH = 255;
    public static final int NAME_MIN_LENGTH = 1;
    public static final int DESCRICAO_MAX_LENGTH = 4000;
    public static final int DESCRICAO_MIN_LENGTH = 1;

    private final Item item;

    public ItemValidator(ValidationHandler aHandler, Item item) {
        super(aHandler);
        this.item = item;
    }

    @Override
    public void validate() {

    }

    private void checkNameConstraints() {
        final var name = this.item.getNome();
        if (name == null) {
            this.validationHandler().append(new Error("'nome' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'nome' should not be empty"));
            return;
        }

        final int length = name.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'nome' must be between 1 and 255 characters"));
        }
    }

    private void checkDescricaoConstraints() {
        final var descricao = this.item.getDescricao();
        if (descricao == null) {
            this.validationHandler().append(new Error("'descricao' should not be null"));
            return;
        }

        if (descricao.isBlank()) {
            this.validationHandler().append(new Error("'descricao' should not be empty"));
            return;
        }

        final int length = descricao.trim().length();
        if (length > DESCRICAO_MAX_LENGTH || length < DESCRICAO_MIN_LENGTH) {
            this.validationHandler().append(new Error("'descricao' must be between 1 and 4000 characters"));
        }
    }
}
