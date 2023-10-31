package be.kdg.prog6.infopoints.ports.in;

import be.kdg.prog6.infopoints.domain.Attraction;

import java.util.List;
import java.util.UUID;

public interface FetchSimilarAttractionsUseCase {
	List<Attraction> fetchSimilarAttractions(UUID attractionUUID);
}
