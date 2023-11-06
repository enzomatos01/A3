package br.com.i4business.money.application.pote.deletar;

import br.com.i4business.money.domain.data.pote.PoteGateway;
import br.com.i4business.money.domain.data.pote.PoteID;

public class DefaultDeletarPoteUseCase extends DeletarPoteUseCase {

    private final PoteGateway poteGateway;

    public DefaultDeletarPoteUseCase(PoteGateway poteGateway) {
        this.poteGateway = poteGateway;
    }

    @Override
    public void execute(final String in) {
        this.poteGateway.deleteById(PoteID.de(in));
    }
}
