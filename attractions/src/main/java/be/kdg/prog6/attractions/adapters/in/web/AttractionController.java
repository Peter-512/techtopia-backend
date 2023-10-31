package be.kdg.prog6.attractions.adapters.in.web;

import be.kdg.prog6.attractions.domain.Attraction;
import be.kdg.prog6.attractions.ports.in.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class AttractionController {
	private final EnqueuingUseCase enqueuingUseCase;
	private final DequeuingUseCase dequeuingUseCase;
	private final FetchAllAttractionsUseCase fetchAllAttractionsUseCase;

	@PostMapping ("/attractions/{attractionUUID}/enqueue")
	public void enqueue(@PathVariable UUID attractionUUID) {
		enqueuingUseCase.enqueue(new EnqueueCommand(new Attraction.AttractionUUID(attractionUUID)));
	}

	@PostMapping ("/attractions/{attractionUUID}/dequeue")
	public void dequeue(@PathVariable UUID attractionUUID) {
		dequeuingUseCase.dequeue(new DequeueCommand(new Attraction.AttractionUUID(attractionUUID)));
	}

	@GetMapping ("/admin/attractions")
	public ResponseEntity<List<Attraction>> getAllAttractions() {
		var attractions = fetchAllAttractionsUseCase.fetchAllAttractions();
		return attractions.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(attractions);
	}
}
