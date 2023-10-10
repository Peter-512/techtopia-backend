package be.kdg.prog6.entrancemanagement.domain;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@ToString
@Slf4j
@Getter
public class Visit {
	private final Visitor visitor;
	private final Gate gate;
	private final LocalDateTime start;
	private LocalDateTime end;

	private Visit(Visitor visitor, Gate gate) {
		this.visitor = visitor;
		this.gate = gate;
		this.start = LocalDateTime.now();
	}

	public static Visit start(Visitor visitor, Gate gate) {
		return new Visit(visitor, gate);
	}

	public void end() {
		this.end = LocalDateTime.now();
		log.info("Visit {} ended", this);
	}
}
