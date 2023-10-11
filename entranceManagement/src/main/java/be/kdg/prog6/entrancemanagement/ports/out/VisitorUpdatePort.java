package be.kdg.prog6.entrancemanagement.ports.out;

import java.util.UUID;

public interface VisitorUpdatePort {
	void visitorEntered(UUID ticketUUID, UUID gateUUID);

	void visitorLeft(UUID ticketUUID, UUID gateUUID);
}
