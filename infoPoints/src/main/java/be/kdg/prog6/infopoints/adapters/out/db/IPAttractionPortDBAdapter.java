package be.kdg.prog6.infopoints.adapters.out.db;

import be.kdg.prog6.infopoints.domain.Attraction;
import be.kdg.prog6.infopoints.ports.out.LoadPort;
import be.kdg.prog6.infopoints.ports.out.UpdatePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
@Slf4j
public class IPAttractionPortDBAdapter implements LoadPort<Attraction>, UpdatePort<Attraction> {
	private final IPAttractionRepository attractionRepository;
	private final IPAttractionMapper iPAttractionMapper = IPAttractionMapper.INSTANCE;

	@Override
	public Optional<Attraction> load(UUID attractionUUID) {
		return attractionRepository.findById(attractionUUID).map(iPAttractionMapper::map);
	}

	@Override
	public List<Attraction> loadAll() {
		return attractionRepository.getAllAttractionsWithTags().stream().map(iPAttractionMapper::map).toList();
	}

	@Override
	public void update(Attraction attraction) {
		attractionRepository.save(iPAttractionMapper.map(attraction));
	}
}
