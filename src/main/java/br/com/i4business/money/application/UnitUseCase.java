package br.com.i4business.money.application;

public abstract class UnitUseCase<IN> {
    public abstract void execute(IN in);
}
