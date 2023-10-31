package be.kdg.prog6.attractions.ports.in;

import be.kdg.prog6.attractions.domain.Attraction;

import java.util.List;

public interface FetchAllAttractionsUseCase {
	List<Attraction> fetchAllAttractions();
}
