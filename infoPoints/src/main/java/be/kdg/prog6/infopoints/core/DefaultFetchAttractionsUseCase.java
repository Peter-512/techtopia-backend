package be.kdg.prog6.infopoints.core;

import be.kdg.prog6.infopoints.domain.Attraction;
import be.kdg.prog6.infopoints.ports.in.FetchAttractionsUseCase;
import be.kdg.prog6.infopoints.ports.out.LoadAttractionPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultFetchAttractionsUseCase implements FetchAttractionsUseCase {
	private final LoadAttractionPort loadAttractionPort;

	@Override
	public List<Attraction> fetchAttractions() {
		return loadAttractionPort.loadAll();
	}
}
