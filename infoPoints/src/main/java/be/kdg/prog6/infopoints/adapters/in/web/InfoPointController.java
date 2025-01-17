package be.kdg.prog6.infopoints.adapters.in.web;

import be.kdg.prog6.infopoints.domain.Attraction;
import be.kdg.prog6.infopoints.domain.Gate;
import be.kdg.prog6.infopoints.ports.in.FetchAttractionsUseCase;
import be.kdg.prog6.infopoints.ports.in.FetchGatesUseCase;
import be.kdg.prog6.infopoints.ports.in.FetchSimilarAttractionsUseCase;
import be.kdg.prog6.infopoints.ports.in.FetchSingleAttractionUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
public class InfoPointController {
	private final FetchAttractionsUseCase fetchAttractionsUseCase;
	private final FetchSingleAttractionUseCase fetchSingleAttractionUseCase;
	private final FetchGatesUseCase fetchGatesUseCase;
	private final FetchSimilarAttractionsUseCase fetchSimilarAttractionsUseCase;

	@GetMapping ("/attractions")
	public ResponseEntity<List<Attraction>> getAttractions() {
		return ResponseEntity.ok(fetchAttractionsUseCase.fetchAttractions());
	}

	@GetMapping ("/attractions/{uuid}")
	public ResponseEntity<Attraction> getAttraction(@PathVariable UUID uuid) {
		final var attraction = fetchSingleAttractionUseCase.fetchAttraction(uuid);
		return attraction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping ("/gates")
	public ResponseEntity<List<Gate>> getGates() {
		return ResponseEntity.ok(fetchGatesUseCase.fetchGates());
	}

	@GetMapping ("/attractions/{uuid}/similar")
	public ResponseEntity<List<Attraction>> getSimilarAttractions(@PathVariable UUID uuid) {
		return ResponseEntity.ok(fetchSimilarAttractionsUseCase.fetchSimilarAttractions(uuid));
	}
}
