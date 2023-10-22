package be.kdg.prog6.attractions.ports.out;

import be.kdg.prog6.attractions.domain.Attraction;

import java.util.Optional;
import java.util.UUID;

public interface AttractionLoadPort {
	Optional<Attraction> loadAttraction(UUID uuid);
}
