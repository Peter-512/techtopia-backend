package be.kdg.prog6.attractions.ports.out;

import be.kdg.prog6.attractions.domain.Attraction;

import java.util.Optional;
import java.util.UUID;

public interface AttractionPort {
	Optional<Attraction> loadAttraction(UUID uuid);

	void saveAttraction(Attraction attraction);
}
