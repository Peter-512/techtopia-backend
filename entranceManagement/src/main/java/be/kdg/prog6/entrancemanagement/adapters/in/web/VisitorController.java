package be.kdg.prog6.entrancemanagement.adapters.in.web;

import be.kdg.prog6.entrancemanagement.ports.in.VisitorEnteredUseCase;
import be.kdg.prog6.entrancemanagement.ports.in.VisitorLeftUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class VisitorController {
	private final VisitorEnteredUseCase visitorEnteredUseCase;
	private final VisitorLeftUseCase visitorLeftUseCase;

	@PostMapping ("/visitor/entered/{amount}")
	public void visitorEntered(@PathVariable int amount) {
		visitorEnteredUseCase.visitorEntered(amount);
	}

	@PostMapping ("/visitor/left/{amount}")
	public void visitorLeft(@PathVariable int amount) {
		visitorLeftUseCase.visitorLeft(amount);
	}
}
