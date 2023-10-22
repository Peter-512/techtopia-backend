package be.kdg.prog6.entrancemanagement.core;

import be.kdg.prog6.entrancemanagement.domain.Visitor;
import be.kdg.prog6.entrancemanagement.ports.in.TransitionVisitorCommand;
import be.kdg.prog6.entrancemanagement.ports.in.VisitorLeavingUseCase;
import be.kdg.prog6.entrancemanagement.ports.out.VisitorGateTransitionCommand;
import be.kdg.prog6.entrancemanagement.ports.out.VisitorUpdatePort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LostTicketVisitorLeafingUseCase implements VisitorLeavingUseCase {
	private final VisitorUpdatePort visitorUpdatePort;

	public LostTicketVisitorLeafingUseCase(@Qualifier ("visitorTransitionAMQPPublisher") VisitorUpdatePort visitorUpdatePort) {
		this.visitorUpdatePort = visitorUpdatePort;
	}

	@Override
	@Transactional
	public boolean visitorLeaving(TransitionVisitorCommand command) {
		Visitor defaultVisitor = Visitor.defaultVisitor();
		defaultVisitor.leave();
		visitorUpdatePort.visitorLeft(new VisitorGateTransitionCommand(
				defaultVisitor, defaultVisitor.getTicketUUID().uuid(), command.gateUUID()));
		return true;
	}
}
