package be.kdg.prog6.entrancemanagement.domain;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@ToString
public class Ticket {
	private final TicketUUID ticketUUID;
	private final LocalDate validOn;

	public Ticket(TicketUUID ticketUUID, LocalDate validOn) {
		this.ticketUUID = ticketUUID;
		this.validOn = validOn;
	}

	public static Ticket create(TicketUUID ticketUUID, LocalDate validOn) {
		return new Ticket(ticketUUID, validOn);
	}

	public boolean isValid() {
		return validOn.isEqual(LocalDate.now());
	}

	public record TicketUUID(UUID uuid) {
	}
}
