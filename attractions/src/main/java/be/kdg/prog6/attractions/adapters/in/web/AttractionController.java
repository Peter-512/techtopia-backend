package be.kdg.prog6.attractions.adapters.in.web;

import be.kdg.prog6.attractions.domain.Attraction;
import be.kdg.prog6.attractions.ports.in.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
public class AttractionController {
	private final EnqueuingUseCase enqueuingUseCase;
	private final DequeuingUseCase dequeuingUseCase;
	private final FetchAllAttractionsUseCase fetchAllAttractionsUseCase;
	private UpdateAttractionUseCase updateAttractionUseCase;

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

	@PutMapping ("/attractions/{attractionUUID}")
	public void updateAttraction(@PathVariable UUID attractionUUID, @RequestBody AttractionDTO attraction) {
		updateAttractionUseCase.updateAttraction(new UpdateAttractionCommand(attractionUUID, attraction.getThroughput(), attraction.getThreshold()));
	}
}
