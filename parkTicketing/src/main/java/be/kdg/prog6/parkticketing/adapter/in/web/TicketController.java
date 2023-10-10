package be.kdg.prog6.parkticketing.adapter.in.web;

import be.kdg.prog6.parkticketing.ports.in.TicketSoldUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class TicketController {
	private final TicketSoldUseCase ticketBoughtUseCase;

	@PostMapping ("/ticket/{ticketUUID}/valid-on/{validOn}")
	public void buyTicket(@PathVariable UUID ticketUUID, @PathVariable LocalDate validOn) {
		ticketBoughtUseCase.buyTicket(ticketUUID, validOn);
	}
}
