package be.kdg.prog6.entrancemanagement.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Getter
@Slf4j
@ToString
@Setter
@NoArgsConstructor
public class Visitor {
	private VisitorUUID visitorUUID;
	private Ticket.TicketUUID ticketUUID;
	private State state;

	public void enter() {
		this.state = State.ENTERED;
	}

	public void leave() {
		this.state = State.LEFT;
	}

	public boolean hasEntered() {
		return state == State.ENTERED;
	}

	public boolean hasLeft() {
		return state == State.LEFT;
	}

	public enum State {
		ENTERED,
		LEFT
	}

	public record VisitorUUID(UUID uuid) {
	}
}
