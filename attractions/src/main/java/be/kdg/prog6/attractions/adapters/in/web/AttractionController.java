package be.kdg.prog6.attractions.adapters.in.web;

import be.kdg.prog6.attractions.domain.Attraction;
import be.kdg.prog6.attractions.ports.in.DequeueCommand;
import be.kdg.prog6.attractions.ports.in.DequeuingUseCase;
import be.kdg.prog6.attractions.ports.in.EnqueueCommand;
import be.kdg.prog6.attractions.ports.in.EnqueuingUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class AttractionController {
	private final EnqueuingUseCase enqueuingUseCase;
	private final DequeuingUseCase dequeuingUseCase;

	@PostMapping ("/attractions/{attractionUUID}/enqueue")
	public void enqueue(@PathVariable UUID attractionUUID) {
		enqueuingUseCase.enqueue(new EnqueueCommand(new Attraction.AttractionUUID(attractionUUID)));

	}

	@PostMapping ("/attractions/{attractionUUID}/dequeue")
	public void dequeue(@PathVariable UUID attractionUUID) {
		dequeuingUseCase.dequeue(new DequeueCommand(new Attraction.AttractionUUID(attractionUUID)));
	}
}
