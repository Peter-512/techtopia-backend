package be.kdg.prog6.entrancemanagement.ports.in;

import java.util.UUID;

public interface VisitorEnteredUseCase {
	boolean visitorEntered(UUID ticketUUID, UUID gateUUID);
}
