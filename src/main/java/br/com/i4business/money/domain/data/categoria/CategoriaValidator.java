package br.com.i4business.money.domain.data.categoria;

import br.com.i4business.money.domain.validation.Error;
import br.com.i4business.money.domain.validation.ValidationHandler;
import br.com.i4business.money.domain.validation.Validator;

public class CategoriaValidator extends Validator {

    public static final int NAME_MAX_LENGTH = 255;
    public static final int NAME_MIN_LENGTH = 1;
    public static final int DESCRICAO_MAX_LENGTH = 4000;
    public static final int DESCRICAO_MIN_LENGTH = 1;
    private final Categoria categoria;

    public CategoriaValidator(ValidationHandler aHandler, Categoria categoria) {
        super(aHandler);
        this.categoria = categoria;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkDescricaoConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.categoria.getNome();
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
        final var descricao = this.categoria.getDescricao();
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
