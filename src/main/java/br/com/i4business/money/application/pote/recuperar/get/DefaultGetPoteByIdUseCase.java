package br.com.i4business.money.application.pote.recuperar.get;

import br.com.i4business.money.domain.data.pote.Pote;
import br.com.i4business.money.domain.data.pote.PoteGateway;
import br.com.i4business.money.domain.data.pote.PoteID;
import br.com.i4business.money.domain.exception.NotFoundException;

public class DefaultGetPoteByIdUseCase extends GetPoteByIdUseCase {

    private final PoteGateway poteGateway;

    public DefaultGetPoteByIdUseCase(final PoteGateway poteGateway) {
        this.poteGateway = poteGateway;
    }
    @Override
    public PoteResponse execute(final String id) {
        final var poteID =  PoteID.de((id));
        return this.poteGateway.findById(poteID)
                .map(PoteResponse::de)
                .orElseThrow(() -> NotFoundException.with(Pote.class, poteID));
    }
}
