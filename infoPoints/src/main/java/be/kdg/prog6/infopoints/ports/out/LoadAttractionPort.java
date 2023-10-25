package be.kdg.prog6.infopoints.ports.out;

import be.kdg.prog6.infopoints.domain.Attraction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadAttractionPort {
	Optional<Attraction> load(UUID attractionUUID);

	List<Attraction> loadAll();
}
