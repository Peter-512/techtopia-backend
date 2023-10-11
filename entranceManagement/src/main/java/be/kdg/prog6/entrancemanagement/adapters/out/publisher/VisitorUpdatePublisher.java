package be.kdg.prog6.entrancemanagement.adapters.out.publisher;

import be.kdg.prog6.common.events.VisitorGateInteraction;
import be.kdg.prog6.entrancemanagement.domain.Visitor;
import be.kdg.prog6.entrancemanagement.ports.out.VisitorUpdatePort;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Component
public class VisitorUpdatePublisher implements VisitorUpdatePort {
	private final ApplicationEventPublisher publisher;

	@Override
	public void visitorEntered(Visitor visitor, UUID ticketUUID, UUID gateUUID) {
		publisher.publishEvent(new VisitorGateInteraction(ticketUUID, gateUUID, LocalDateTime.now(), Visitor.State.ENTERED.name()));
	}

	@Override
	public void visitorLeft(Visitor visitor, UUID ticketUUID, UUID gateUUID) {
		publisher.publishEvent(new VisitorGateInteraction(ticketUUID, gateUUID, LocalDateTime.now(), Visitor.State.LEFT.name()));
	}
}
