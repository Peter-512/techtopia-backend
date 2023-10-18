package be.kdg.prog6.attractions.adapters.out.db;

import be.kdg.prog6.attractions.domain.Attraction;
import be.kdg.prog6.attractions.ports.out.AttractionPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
@Slf4j
public class AttractionDBAdapter implements AttractionPort {
	private final AttractionRepository attractionRepository;
	private final AttractionMapper mapper = AttractionMapper.INSTANCE;

	@Override
	public Optional<Attraction> loadAttraction(UUID uuid) {
		final Optional<AttractionJpaEntity> optionalAttraction = attractionRepository.findById(uuid);
		log.info("Attraction loaded: {}", optionalAttraction.get());
		return optionalAttraction.map(mapper::map);
	}

	@Override
	public void saveAttraction(Attraction attraction) {
		AttractionJpaEntity entity = mapper.map(attraction);
		log.info("Attraction saved: {}", entity);
		attractionRepository.save(entity);
	}
}
