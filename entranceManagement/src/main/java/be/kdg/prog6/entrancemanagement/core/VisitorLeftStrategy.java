package be.kdg.prog6.entrancemanagement.core;

import be.kdg.prog6.entrancemanagement.ports.in.VisitorLeftUseCase;
import lombok.AllArgsConstructor;

import java.util.UUID;

// FIXME
@AllArgsConstructor
//@Component
public class VisitorLeftStrategy {
	private final VisitorLeftUseCase visitorLeftUseCase;

	public void visitorLeft(UUID ticketUUID, UUID gateUUID) {
		visitorLeftUseCase.visitorLeft(ticketUUID, gateUUID);
	}

	public void visitorLeft() {
		//		visitorLeftUseCase.visitorLeft();
	}
}
