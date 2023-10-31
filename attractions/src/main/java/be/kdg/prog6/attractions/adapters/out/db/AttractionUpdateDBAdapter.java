package be.kdg.prog6.attractions.adapters.out.db;

import be.kdg.prog6.attractions.domain.Attraction;
import be.kdg.prog6.attractions.ports.out.AttractionLoadPort;
import be.kdg.prog6.attractions.ports.out.AttractionUpdatePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
@Slf4j
public class AttractionUpdateDBAdapter implements AttractionUpdatePort, AttractionLoadPort {
	private final AttractionRepository attractionRepository;
	private final AttractionMapper mapper = AttractionMapper.INSTANCE;

	@Override
	public Optional<Attraction> loadAttraction(UUID uuid) {
		final Optional<AttractionJpaEntity> optionalAttraction = attractionRepository.findById(uuid);
		log.info("Attraction loaded: {}", optionalAttraction.get());
		return optionalAttraction.map(mapper::map);
	}

	@Override
	public List<Attraction> loadAllAttractions() {
		return attractionRepository.findAll().stream().map(mapper::map).toList();
	}

	@Override
	public void updateAttraction(Attraction attraction) {
		AttractionJpaEntity entity = mapper.map(attraction);
		log.info("Attraction saved: {}", entity);
		attractionRepository.save(entity);
	}
}
