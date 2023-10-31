package be.kdg.prog6.infopoints.core;

import be.kdg.prog6.infopoints.adapters.out.db.IPAttractionMapper;
import be.kdg.prog6.infopoints.adapters.out.db.IPAttractionRepository;
import be.kdg.prog6.infopoints.domain.Attraction;
import be.kdg.prog6.infopoints.ports.in.FetchSimilarAttractionsUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DefaultFetchSimilarAttractionsUseCase implements FetchSimilarAttractionsUseCase {
	private final IPAttractionRepository attractionRepository;
	private final IPAttractionMapper attractionMapper = IPAttractionMapper.INSTANCE;

	@Override
	public List<Attraction> fetchSimilarAttractions(UUID attractionUUID) {
		return attractionRepository.getAllAttractionsWithSimilarTags(attractionUUID)
		                           .stream()
		                           .map(attractionMapper::map)
		                           .toList();
	}
}
