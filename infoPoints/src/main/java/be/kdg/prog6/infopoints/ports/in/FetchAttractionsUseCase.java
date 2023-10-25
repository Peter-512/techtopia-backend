package be.kdg.prog6.infopoints.ports.in;

import be.kdg.prog6.infopoints.domain.Attraction;

import java.util.List;

public interface FetchAttractionsUseCase {
	List<Attraction> fetchAttractions();
}
