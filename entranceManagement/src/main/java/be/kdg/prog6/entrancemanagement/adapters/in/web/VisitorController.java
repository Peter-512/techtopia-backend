package be.kdg.prog6.entrancemanagement.adapters.in.web;

import be.kdg.prog6.entrancemanagement.ports.in.VisitorEnteredUseCase;
import be.kdg.prog6.entrancemanagement.ports.in.VisitorLeftUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@AllArgsConstructor
@RestController
public class VisitorController {
	private final VisitorEnteredUseCase visitorEnteredUseCase;
	private final VisitorLeftUseCase visitorLeftUseCase;

	@PostMapping ("/visitor/enter/{ticketUUID}/gate/{gateUUID}")
	public ResponseEntity<String> visitorEntered(@PathVariable UUID ticketUUID, @PathVariable UUID gateUUID) {
		final boolean ticketValid = visitorEnteredUseCase.visitorEntered(ticketUUID, gateUUID);
		if (!ticketValid) {
			return ResponseEntity.badRequest().body("Ticket not valid");
		}
		return ResponseEntity.ok().build();
	}

	@PostMapping ("/visitor/leave/{ticketUUID}/gate/{gateUUID}")
	public void visitorLeft(@PathVariable UUID ticketUUID, @PathVariable UUID gateUUID) {
		visitorLeftUseCase.visitorLeft(ticketUUID, gateUUID);
	}
}
