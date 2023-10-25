package be.kdg.prog6.infopoints.ports.in;

import be.kdg.prog6.infopoints.domain.Attraction;

import java.util.Optional;
import java.util.UUID;

public interface FetchSingleAttractionUseCase {
	Optional<Attraction> fetchAttraction(UUID attractionUUID);
}
