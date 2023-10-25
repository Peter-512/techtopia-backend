package be.kdg.prog6.infopoints.core;

import be.kdg.prog6.infopoints.domain.Attraction;
import be.kdg.prog6.infopoints.ports.in.FetchSingleAttractionUseCase;
import be.kdg.prog6.infopoints.ports.out.LoadAttractionPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DefaultFetchSingleAttractionUseCase implements FetchSingleAttractionUseCase {
	private final LoadAttractionPort loadAttractionPort;

	@Override
	public Optional<Attraction> fetchAttraction(UUID attractionUUID) {
		return loadAttractionPort.load(attractionUUID);
	}
}
