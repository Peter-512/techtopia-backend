package be.kdg.prog6.entrancemanagement.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Getter
@Slf4j
@ToString
@Setter
public class Visitor {
	private final VisitorUUID visitorUUID;
	private final Ticket ticket;

	private Visitor(VisitorUUID visitorUUID, Ticket ticket) {
		this.visitorUUID = visitorUUID;
		this.ticket = ticket;
	}

	public static Visitor enter(Ticket ticket) {
		return new Visitor(new VisitorUUID(UUID.randomUUID()), ticket);
	}

	public Visit enter(Gate gate) {
		return Visit.start(this, gate);
	}

	public void leaves(Gate.GateUUID gateUUID) {
		//		TODO: implement
		log.info("Visitor {} leaves through gate {}", this, gateUUID);
	}

	public record VisitorUUID(UUID uuid) {
	}
}
