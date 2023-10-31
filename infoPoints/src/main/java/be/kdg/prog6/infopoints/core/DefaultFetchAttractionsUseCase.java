package be.kdg.prog6.infopoints.core;

import be.kdg.prog6.infopoints.domain.Attraction;
import be.kdg.prog6.infopoints.ports.in.FetchAttractionsUseCase;
import be.kdg.prog6.infopoints.ports.out.LoadPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultFetchAttractionsUseCase implements FetchAttractionsUseCase {
	private final LoadPort<Attraction> loadPort;

	@Override
	public List<Attraction> fetchAttractions() {
		return loadPort.loadAll();
	}
}
