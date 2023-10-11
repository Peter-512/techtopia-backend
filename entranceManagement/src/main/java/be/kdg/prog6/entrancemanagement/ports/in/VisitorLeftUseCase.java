package be.kdg.prog6.entrancemanagement.ports.in;

import java.util.UUID;

public interface VisitorLeftUseCase {
	void visitorLeft(UUID ticketUUID, UUID gateUUID);
}
