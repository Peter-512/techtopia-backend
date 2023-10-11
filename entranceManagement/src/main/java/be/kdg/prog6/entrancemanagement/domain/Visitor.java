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
	private State state;

	public Visitor(VisitorUUID visitorUUID, State state) {
		this.visitorUUID = visitorUUID;
		this.state = state;
	}

	public void enter() {
		this.state = State.ENTERED;
	}

	public void leave() {
		this.state = State.LEFT;
	}

	public boolean hasEntered() {
		return state == State.ENTERED;
	}

	public enum State {
		ENTERED,
		LEFT
	}

	public record VisitorUUID(UUID uuid) {
	}
}
