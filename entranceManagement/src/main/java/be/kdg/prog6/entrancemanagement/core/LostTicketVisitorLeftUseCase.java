package be.kdg.prog6.entrancemanagement.core;

import be.kdg.prog6.entrancemanagement.ports.in.VisitorLeftUseCase;
import be.kdg.prog6.entrancemanagement.ports.out.VisitorUpdatePort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.UUID;

//TODO: figure out how to use VisitorLeftStrategy to conditionally inject the right VisitorLeftUseCase
//@Service
public class LostTicketVisitorLeftUseCase implements VisitorLeftUseCase {
	private static final UUID LOST_TICKET_UUID = new UUID(0, 0);

	private final VisitorUpdatePort visitorUpdatePort;

	public LostTicketVisitorLeftUseCase(@Qualifier ("visitorUpdatePublisher") VisitorUpdatePort visitorUpdatePort) {
		this.visitorUpdatePort = visitorUpdatePort;
	}

	@Override
	@Transactional
	public void visitorLeft(UUID _uuid, UUID gateUUID) {
		visitorUpdatePort.visitorLeft(LOST_TICKET_UUID, gateUUID);
	}
}
