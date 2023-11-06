package br.com.i4business.money.domain.data.despesa;

import br.com.i4business.money.domain.validation.ValidationHandler;
import br.com.i4business.money.domain.validation.Validator;

public class DespesaValidator extends Validator {
    private final Despesa despesa;
    protected DespesaValidator(ValidationHandler aHandler, Despesa despesa) {
        super(aHandler);
        this.despesa = despesa;
    }

    @Override
    public void validate() {

    }
}
