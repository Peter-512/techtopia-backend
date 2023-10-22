package be.kdg.prog6.entrancemanagement.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@ToString
@Setter
@NoArgsConstructor
public class Ticket {
	public static UUID LOST_TICKET = UUID.fromString("00000000-0000-0000-0000-000000000000");
	private TicketUUID ticketUUID;
	private LocalDate validOn;

	public boolean isValid() {
		return validOn.isEqual(LocalDate.now());
	}

	public record TicketUUID(UUID uuid) {
	}
}
