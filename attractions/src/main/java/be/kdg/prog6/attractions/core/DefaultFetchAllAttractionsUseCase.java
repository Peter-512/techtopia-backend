package be.kdg.prog6.attractions.core;

import be.kdg.prog6.attractions.domain.Attraction;
import be.kdg.prog6.attractions.ports.in.FetchAllAttractionsUseCase;
import be.kdg.prog6.attractions.ports.out.AttractionLoadPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultFetchAllAttractionsUseCase implements FetchAllAttractionsUseCase {
	private final AttractionLoadPort loadPort;

	@Override
	public List<Attraction> fetchAllAttractions() {
		return loadPort.loadAllAttractions();
	}
}
