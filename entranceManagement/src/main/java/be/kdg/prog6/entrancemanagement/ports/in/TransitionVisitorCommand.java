package be.kdg.prog6.entrancemanagement.ports.in;

import java.util.UUID;

public record TransitionVisitorCommand(UUID ticketUUID, UUID gateUUID) {
}
