package be.kdg.prog6.entrancemanagement.core;

import be.kdg.prog6.entrancemanagement.ports.in.TransitionVisitorCommand;
import be.kdg.prog6.entrancemanagement.ports.in.VisitorLeavingUseCase;
import be.kdg.prog6.entrancemanagement.ports.out.VisitorUpdatePort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.UUID;

//TODO: figure out how to use VisitorLeftStrategy to conditionally inject the right VisitorLeavingUseCase
//@Service
public class LostTicketVisitorLeafingUseCase implements VisitorLeavingUseCase {
	private static final UUID LOST_TICKET_UUID = new UUID(0, 0);

	private final VisitorUpdatePort visitorUpdatePort;

	public LostTicketVisitorLeafingUseCase(@Qualifier ("visitorUpdatePublisher") VisitorUpdatePort visitorUpdatePort) {
		this.visitorUpdatePort = visitorUpdatePort;
	}

	@Override
	@Transactional
	public boolean visitorLeaving(TransitionVisitorCommand command) {
		//		visitorUpdatePort.visitorLeft(new Visitor(), LOST_TICKET_UUID);
		return true;
	}
}
