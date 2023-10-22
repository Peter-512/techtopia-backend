package be.kdg.prog6.entrancemanagement.adapters.in.web;

import be.kdg.prog6.entrancemanagement.core.VisitorLeavingStrategy;
import be.kdg.prog6.entrancemanagement.ports.in.TransitionVisitorCommand;
import be.kdg.prog6.entrancemanagement.ports.in.VisitorEnteringUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@AllArgsConstructor
@RestController
public class VisitorController {
	private final VisitorEnteringUseCase visitorEnteringUseCase;
	private final VisitorLeavingStrategy visitorLeavingStrategy;

	@PostMapping ("/visitor/enter/{ticketUUID}/gate/{gateUUID}")
	public ResponseEntity<String> visitorEntered(@PathVariable UUID ticketUUID, @PathVariable UUID gateUUID) {
		final boolean ticketValid = visitorEnteringUseCase.visitorEntering(new TransitionVisitorCommand(ticketUUID, gateUUID));
		if (!ticketValid) {
			return ResponseEntity.badRequest().body("Ticket not valid");
		}
		return ResponseEntity.ok().build();
	}

	@PostMapping ("/visitor/leave/{ticketUUID}/gate/{gateUUID}")
	public ResponseEntity<String> visitorLeft(@PathVariable UUID ticketUUID, @PathVariable UUID gateUUID) {

		final boolean ticketValid = visitorLeavingStrategy.getStrategy(ticketUUID)
		                                                  .visitorLeaving(new TransitionVisitorCommand(ticketUUID, gateUUID));
		if (!ticketValid) {
			return ResponseEntity.badRequest().body("Ticket not valid");
		}
		return ResponseEntity.ok().build();
	}
}
