package be.kdg.prog6.entrancemanagement.ports.out;

import be.kdg.prog6.entrancemanagement.domain.Visitor;

import java.util.UUID;

public interface VisitorUpdatePort {
	void visitorEntered(Visitor visitor, UUID ticketUUID, UUID gateUUID);

	void visitorLeft(UUID ticketUUID, UUID gateUUID);
}
