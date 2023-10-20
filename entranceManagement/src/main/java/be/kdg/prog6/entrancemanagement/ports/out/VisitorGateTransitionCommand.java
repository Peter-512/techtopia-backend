package be.kdg.prog6.entrancemanagement.ports.out;

import be.kdg.prog6.entrancemanagement.domain.Visitor;

import java.util.UUID;

public record VisitorGateTransitionCommand(Visitor visitor, UUID ticketUUID, UUID gateUUID) {
}
